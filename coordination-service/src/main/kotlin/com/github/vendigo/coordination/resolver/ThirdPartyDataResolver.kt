package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.flow.SubscribedServiceResolver
import com.github.vendigo.coordination.model.ServiceName
import org.springframework.stereotype.Component

@Component
class ThirdPartyDataResolver(val ttClient: TtClient) : SubscribedServiceResolver<String>(ServiceName.THIRD_PARTY) {
    override suspend fun resolveSubscribed(context: FlowContext): String {
        return ttClient.requestThirdPartyData(context.normalizedConsumer.await().ssn)
    }
}