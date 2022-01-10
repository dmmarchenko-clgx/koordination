package com.github.vendigo.ttmocks

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

@RestController
class Controller {

    val log = LoggerFactory.getLogger(Controller::class.java)!!

    @PostMapping("/consumer/normalize")
    fun normalizeConsumer(@RequestBody consumer: ConsumerDetails): ConsumerDetails {
        log.info("Normalizing consumer")
        delay(100)
        return consumer
            .copy(
                firstName = consumer.firstName.uppercase(),
                lastName = consumer.lastName.uppercase()
            )
    }

    @PostMapping("/consumer/validate")
    fun validateConsumer(@RequestBody consumer: ConsumerDetails): List<String> {
        log.info("Validating consumer")
        delay(100)

        if (consumer.ssn.length != 6) {
            return listOf("SSN should be 6 chars long")
        }

        return listOf()
    }

    @PostMapping("/consumer/match")
    fun matchConsumer(@RequestBody matchRequest: ConsumerMatchRequest): Long {
        log.info("Matching consumer")
        delay(100)
        return 10
    }

    @GetMapping("/consumer/{consumerMasterId}/history")
    fun getConsumerHistory(@PathVariable("consumerMasterId") consumerMasterId: Long): ConsumerHistory {
        log.info("Getting consumer history")
        delay(500)
        return ConsumerHistory(
            consumerMasterId,
            consumerDetails = listOf(ConsumerDetails("10001", LocalDate.of(1990, Month.APRIL, 2), "Larisa", "Buro")),
            addresses = listOf(Address("San Diego", "CA", "19600")),
            previousInquiries = listOf(PreviousInquiry(140, LocalDateTime.of(2021, Month.AUGUST, 8, 12, 0)))
        )
    }

    @GetMapping("/address/standardize")
    fun standardizeAddress(@RequestParam("rawAddress") rawAddress: String): Address {
        log.info("Standardizing address")
        delay(200)
        val (city, state, postalCode) = rawAddress.split(", ")
        return Address(city, state, postalCode)
    }

    @GetMapping("/generate-id")
    fun generateId(): String {
        log.info("Generating id")
        delay(100)
        return generateRandomId()
    }

    @GetMapping("/merchant/{mid}/configuration")
    fun getMerchantConfiguration(@PathVariable("mid") mid: Long): MerchantConfiguration {
        log.info("Getting merchant configuration")
        delay(100)
        return MerchantConfiguration(
            mid, "Peter Merchant",
            listOf(Subscription(ServiceName.TELETRACK_REPORT), Subscription(ServiceName.SCORE), Subscription(ServiceName.THIRD_PARTY))
        )
    }

    @GetMapping("/third-party-data")
    fun requestThirdPartyData(@RequestParam("ssn") ssn: String): String {
        log.info("Requesting third party data")
        delay(600)

        if (ssn == "100000") {
            throw IllegalArgumentException("Unable to get third party data")
        }

        return "Third party data for $ssn"
    }

    @GetMapping("/score")
    fun requestScore(@RequestParam("consumerId") consumerId: Long): String {
        log.info("Requesting score")
        delay(200)
        return "Score for consumer $consumerId is 200"
    }

    @PostMapping("/inquiry")
    fun saveInquiry(@RequestBody inquiry: SaveInquiryRequest): Long {
        log.info("Saving inquiry")
        delay(300)
        return 1
    }
}