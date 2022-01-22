package com.github.vendigo.coordination.flow

import com.github.vendigo.coordination.model.InquiryRequest
import com.github.vendigo.coordination.resolver.Resolvers
import kotlinx.coroutines.CoroutineScope

class FlowContext(
    val request: InquiryRequest,
    val scope: CoroutineScope,
    resolvers: Resolvers
) {
    val transactionId by resolver(resolvers.transactionId)
    val normalizedConsumer by resolver(resolvers.normalizedConsumer)
    val standardizedAddress by resolver(resolvers.standardizedAddress)
    val merchantConfig by resolver(resolvers.merchantConfig)
    val consumerValidationErrors by resolver(resolvers.consumerValidationErrors)
    val consumerMasterId by resolver(resolvers.consumerMasterId)
    val consumerId by resolver(resolvers.consumerId)
    val scoreResponse by resolver(resolvers.score)
    val thirdPartyData by resolver(resolvers.thirdPartyData)
    val consumerHistory by resolver(resolvers.consumerHistory)
    val inquiryResponse by resolver(resolvers.inquiryResponse)
}