package com.github.vendigo.coordination.flow

import com.github.vendigo.coordination.model.ServiceName
import org.slf4j.LoggerFactory

abstract class SubscribedServiceResolver<T>(val service: ServiceName) : AsyncResolver<T?> {

    private val log = LoggerFactory.getLogger(this::class.java)!!

    abstract suspend fun resolveSubscribed(context: FlowContext): T

    override suspend fun resolve(context: FlowContext): T? {
        if (service !in context.request.requestedServices) {
            log.info("Service $service is not requested")
            return null
        }

        val merchantConfig = context.merchantConfig.await()
        val subscribedServices = merchantConfig.subscriptions
            .asSequence()
            .filter { it.enabled }
            .map { it.serviceName }
            .toSet()
        if (service !in subscribedServices) {
            return null
        }
        return resolveSubscribed(context)
    }
}