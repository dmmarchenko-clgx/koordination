package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.model.ConsumerDetails
import org.springframework.stereotype.Component

@Component
class NormalizedConsumerResolver(val ttClient: TtClient) : AsyncResolver<ConsumerDetails> {
    override suspend fun resolve(context: FlowContext): ConsumerDetails {
        return ttClient.normalizeConsumer(context.request.consumer)
    }
}