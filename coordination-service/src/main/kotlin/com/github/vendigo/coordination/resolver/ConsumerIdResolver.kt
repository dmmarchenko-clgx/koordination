package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.load
import com.github.vendigo.coordination.model.SaveInquiryRequest
import org.springframework.stereotype.Component

@Component
class ConsumerIdResolver(val ttClient: TtClient) : AsyncResolver<Long> {

    override suspend fun resolve(context: FlowContext): Long = with(context) {
        load {
            consumerMasterId
            normalizedConsumer
            standardizedAddress
            transactionId
        }

        val saveInquiryRequest =
            SaveInquiryRequest(
                consumerMasterId.await(),
                normalizedConsumer.await(),
                standardizedAddress.await(),
                transactionId.await()
            )
        return ttClient.saveInquiry(saveInquiryRequest)
    }

}