package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.load
import com.github.vendigo.coordination.model.InquiryResponse
import org.springframework.stereotype.Component

@Component
class InquiryResponseResolver(val ttClient: TtClient) : AsyncResolver<InquiryResponse> {

    override suspend fun resolve(context: FlowContext): InquiryResponse = with(context) {
        load {
            merchantConfig
            transactionId
            consumerMasterId
            consumerId
            consumerHistory
            thirdPartyData
            scoreResponse
            validationErrors
        }

        return InquiryResponse(
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

}