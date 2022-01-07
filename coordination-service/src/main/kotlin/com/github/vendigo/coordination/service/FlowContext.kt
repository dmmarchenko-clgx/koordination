package com.github.vendigo.coordination.service

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.model.*
import com.github.vendigo.coordination.utils.lazyAsync
import kotlinx.coroutines.CoroutineScope

class FlowContext(
    private val request: InquiryRequest,
    private val ttClient: TtClient,
    scope: CoroutineScope
) {

    private val transactionId by lazyAsync(scope) {
        ttClient.generateId()
    }
    private val normalizedConsumer by lazyAsync(scope) {
        ttClient.normalizeConsumer(request.consumer)
    }
    private val standardizedAddress by lazyAsync(scope) {
        ttClient.standardizeAddress(request.address)
    }
    private val merchantConfig by lazyAsync(scope) {
        ttClient.getMerchantConfiguration(request.mid)
    }
    private val validationErrors by lazyAsync(scope) {
        ttClient.validateConsumer(getNormalizedConsumer())
    }
    private val consumerMasterId by lazyAsync(scope) {
        val consumer = getNormalizedConsumer()
        val address = getStandardizedAddress()
        ttClient.matchConsumer(ConsumerMatchRequest(consumer.ssn, consumer.dateOfBirth, address.state))
    }
    private val consumerId by lazyAsync(scope) {
        val saveInquiryRequest =
            SaveInquiryRequest(
                getConsumerMasterId(),
                getNormalizedConsumer(),
                getStandardizedAddress(),
                getTransactionId()
            )
        ttClient.saveInquiry(saveInquiryRequest)
    }
    private val scoreResponse by lazyAsync(scope) {
        ttClient.requestScore(getConsumerId())
    }
    private val thirdPartyData by lazyAsync(scope) {
        ttClient.requestThirdPartyData(getNormalizedConsumer().ssn)
    }
    private val consumerHistory by lazyAsync(scope) {
        ttClient.getConsumerHistory(getConsumerMasterId())
    }

    suspend fun getTransactionId(): String {
        return transactionId.await()
    }

    suspend fun getNormalizedConsumer(): ConsumerDetails {
        return normalizedConsumer.await()
    }

    suspend fun getStandardizedAddress(): Address {
        return standardizedAddress.await()
    }

    suspend fun getMerchantConfig(): MerchantConfiguration {
        return merchantConfig.await()
    }

    suspend fun getValidationErrors(): List<String> {
        return validationErrors.await()
    }

    suspend fun getConsumerMasterId(): Long {
        return consumerMasterId.await()
    }

    suspend fun getConsumerId(): Long {
        return consumerId.await()
    }

    suspend fun getScoreResponse(): String {
        return scoreResponse.await()
    }

    suspend fun getThirdPartyData(): String {
        return thirdPartyData.await()
    }

    suspend fun getConsumerHistory(): ConsumerHistory {
        return consumerHistory.await()
    }
}