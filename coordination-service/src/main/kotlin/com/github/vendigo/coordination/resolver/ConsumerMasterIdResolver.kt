package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.load
import com.github.vendigo.coordination.model.ConsumerMatchRequest
import org.springframework.stereotype.Component

@Component
class ConsumerMasterIdResolver(val ttClient: TtClient) : AsyncResolver<Long> {

    override suspend fun resolve(context: FlowContext): Long = with(context) {
        load {
            normalizedConsumer
            standardizedAddress
        }

        val consumer = normalizedConsumer.await()
        val address = standardizedAddress.await()
        return ttClient.matchConsumer(ConsumerMatchRequest(consumer.ssn, consumer.dateOfBirth, address.state))
    }

}