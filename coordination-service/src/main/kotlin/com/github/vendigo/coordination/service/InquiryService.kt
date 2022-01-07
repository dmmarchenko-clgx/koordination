package com.github.vendigo.coordination.service

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.model.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class InquiryService(val ttClient: TtClient) {

    suspend fun processInquiry(request: InquiryRequest): InquiryResponse = coroutineScope {
        val transactionId = async { ttClient.generateId() }
        val consumer = async { ttClient.normalizeConsumer(request.consumer) }
        val address = async { ttClient.standardizeAddress(request.address) }

        val merchantConfig = async { ttClient.getMerchantConfiguration(request.mid) }
        val validationErrors = async { ttClient.validateConsumer(consumer.await()) }
        val consumerMasterId =
            async { ttClient.matchConsumer(createConsumerMatchRequest(consumer.await(), address.await())) }
        val saveInquiryRequest =
            SaveInquiryRequest(consumerMasterId.await(), consumer.await(), address.await(), transactionId.await())
        val consumerId = async { ttClient.saveInquiry(saveInquiryRequest) }
        val scoreResponse = async { ttClient.requestScore(consumerMasterId.await()) }
        val thirdPartyData = async { ttClient.requestThirdPartyData(consumer.await().ssn) }
        val consumerHistory = async { ttClient.getConsumerHistory(consumerMasterId.await()) }

        InquiryResponse(
            merchantName = merchantConfig.await().merchantName,
            transactionId = transactionId.await(),
            consumerMasterId = consumerMasterId.await(),
            consumerId = consumerId.await(),
            consumerHistory = consumerHistory.await(),
            thirdPartyResponse = thirdPartyData.await(),
            scoringResponse = scoreResponse.await(),
            errors = validationErrors.await()
        )
    }

    fun createConsumerMatchRequest(consumer: ConsumerDetails, address: Address) =
        ConsumerMatchRequest(consumer.ssn, consumer.dateOfBirth, address.state)
}