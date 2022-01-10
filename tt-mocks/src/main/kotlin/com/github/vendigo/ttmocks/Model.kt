package com.github.vendigo.ttmocks

import java.time.LocalDate
import java.time.LocalDateTime

data class ConsumerHistory(
    val consumerMasterId: Long, val consumerDetails: List<ConsumerDetails>,
    val addresses: List<Address> = listOf(), val previousInquiries: List<PreviousInquiry> = listOf()
)

data class ConsumerDetails(val ssn: String, val dateOfBirth: LocalDate, val firstName: String, val lastName: String)

data class Address(val city: String, val state: String, val postalCode: String)

data class PreviousInquiry(val mid: Long, val inquiryDate: LocalDateTime)

data class ConsumerMatchRequest(val ssn: String, val dateOfBirth: LocalDate, val state: String)

data class MerchantConfiguration(val mid: Long, val merchantName: String, val subscriptions: List<Subscription>)

data class Subscription(val serviceName: ServiceName, val enabled: Boolean = true)

data class SaveInquiryRequest(
    val consumerMasterId: Long, val consumerDetails: ConsumerDetails,
    val address: Address, val transactionId: String
)

enum class ServiceName {
    TELETRACK_REPORT, SCORE, THIRD_PARTY
}