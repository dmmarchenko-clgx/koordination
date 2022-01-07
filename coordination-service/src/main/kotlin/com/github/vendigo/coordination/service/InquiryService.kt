package com.github.vendigo.coordination.service

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.model.InquiryRequest
import com.github.vendigo.coordination.model.InquiryResponse
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class InquiryService(val ttClient: TtClient) {

    suspend fun processInquiry(request: InquiryRequest): InquiryResponse = coroutineScope {
        val context = FlowContext(request, ttClient, this)

        InquiryResponse(
            merchantName = context.getMerchantConfig().merchantName,
            transactionId = context.getTransactionId(),
            consumerMasterId = context.getConsumerMasterId(),
            consumerId = context.getConsumerId(),
            consumerHistory = context.getConsumerHistory(),
            thirdPartyResponse = context.getThirdPartyData(),
            scoringResponse = context.getScoreResponse(),
            errors = context.getValidationErrors()
        )
    }
}