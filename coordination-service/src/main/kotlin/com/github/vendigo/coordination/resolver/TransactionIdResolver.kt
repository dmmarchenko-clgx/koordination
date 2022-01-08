package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import org.springframework.stereotype.Component

@Component
class TransactionIdResolver(val ttClient: TtClient) : AsyncResolver<String> {

    override suspend fun resolve(context: FlowContext): String {
        return ttClient.generateId()
    }

}