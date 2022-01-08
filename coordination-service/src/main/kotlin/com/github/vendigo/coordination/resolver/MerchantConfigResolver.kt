package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.model.MerchantConfiguration
import org.springframework.stereotype.Component

@Component
class MerchantConfigResolver(val ttClient: TtClient) : AsyncResolver<MerchantConfiguration> {

    override suspend fun resolve(context: FlowContext): MerchantConfiguration {
        return ttClient.getMerchantConfiguration(context.request.mid)
    }

}