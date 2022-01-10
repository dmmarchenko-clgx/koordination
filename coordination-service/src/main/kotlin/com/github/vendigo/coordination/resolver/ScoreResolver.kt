package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.SubscribedServiceResolver
import com.github.vendigo.coordination.model.ServiceName
import org.springframework.stereotype.Component

@Component
class ScoreResolver(val ttClient: TtClient) : SubscribedServiceResolver<String>(ServiceName.SCORE) {
    override suspend fun resolveSubscribed(context: FlowContext): String {
        return ttClient.requestScore(context.consumerId.await())
    }
}