package com.github.vendigo.coordination.resolver

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.flow.AsyncResolver
import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.model.Address
import org.springframework.stereotype.Component

@Component
class StandardizedAddressResolver(val ttClient: TtClient) : AsyncResolver<Address> {

    override suspend fun resolve(context: FlowContext): Address {
        return ttClient.standardizeAddress(context.request.address)
    }

}