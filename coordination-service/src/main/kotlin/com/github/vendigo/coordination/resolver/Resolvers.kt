package com.github.vendigo.coordination.resolver

import org.springframework.stereotype.Component

@Component
class Resolvers(
    val transactionId: TransactionIdResolver,
    val normalizedConsumer: NormalizedConsumerResolver,
    val standardizedAddress: StandardizedAddressResolver,
    val merchantConfig: MerchantConfigResolver,
    val consumerHistory: ConsumerHistoryResolver,
    val consumerId: ConsumerIdResolver,
    val consumerMasterId: ConsumerMasterIdResolver,
    val score: ScoreResolver,
    val thirdPartyData: ThirdPartyDataResolver,
    val validationErrors: ValidationErrorsResolver,
    val inquiryResponse: InquiryResponseResolver
)