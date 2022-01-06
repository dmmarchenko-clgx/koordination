package com.github.vendigo.coordination.service

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.model.*
import org.springframework.stereotype.Service

@Service
class InquiryService(val ttClient: TtClient) {

    fun processInquiry(request: InquiryRequest): InquiryResponse {
        val transactionId = ttClient.generateId()
        val consumer = ttClient.normalizeConsumer(request.consumer)
        val address = ttClient.standardizeAddress(request.address)

        val merchantConfig = ttClient.getMerchantConfiguration(request.mid)
        val validationErrors = ttClient.validateConsumer(consumer)
        val consumerMasterId = ttClient.matchConsumer(ConsumerMatchRequest(consumer.ssn, consumer.dateOfBirth, address.state))
        val consumerDetails = ConsumerDetails(consumer.ssn, consumer.dateOfBirth, consumer.firstName, consumer.lastName)
        val consumerId = ttClient.saveInquiry(SaveInquiryRequest(consumerMasterId, consumerDetails, address, transactionId))
        val scoreResponse = ttClient.requestScore(consumerMasterId)
        val thirdPartyData = ttClient.requestThirdPartyData(consumer.ssn)
        val consumerHistory = ttClient.getConsumerHistory(consumerMasterId)

        return InquiryResponse(
            merchantName = merchantConfig.merchantName,
            transactionId = transactionId,
            consumerMasterId = consumerMasterId,
            consumerId = consumerId,
            consumerHistory = consumerHistory,
            thirdPartyResponse = thirdPartyData,
            scoringResponse = scoreResponse,
            errors = validationErrors
        )
    }
}