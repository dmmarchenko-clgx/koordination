package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.SubscribedServiceResolver
import com.github.vendigo.coordination.model.ConsumerHistory
import com.github.vendigo.coordination.model.ServiceName
import org.springframework.stereotype.Component

@Component
class ConsumerHistoryResolver(val ttClient: TtClient) : SubscribedServiceResolver<ConsumerHistory>(ServiceName.TELETRACK_REPORT) {
    override suspend fun resolveSubscribed(context: FlowContext): ConsumerHistory {
        return ttClient.getConsumerHistory(context.consumerMasterId.await())
    }
}