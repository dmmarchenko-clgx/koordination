package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.model.ConsumerHistory
import org.springframework.stereotype.Component

@Component
class ConsumerHistoryResolver(val ttClient: TtClient) : AsyncResolver<ConsumerHistory> {
    override suspend fun resolve(context: FlowContext): ConsumerHistory {
        return ttClient.getConsumerHistory(context.consumerMasterId.await())
    }
}