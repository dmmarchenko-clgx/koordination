package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import org.springframework.stereotype.Component

@Component
class ValidationErrorsResolver(val ttClient: TtClient) : AsyncResolver<List<String>> {

    override suspend fun resolve(context: FlowContext): List<String> {
        return ttClient.validateConsumer(context.normalizedConsumer.await())
    }

}