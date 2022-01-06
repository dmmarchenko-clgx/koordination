package com.github.vendigo.coordinationservice

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(name = "tt-mocks", url = "http://localhost:8070")
interface TtClient {

    @PostMapping("/consumer/normalize")
    fun normalizeConsumer(@RequestBody consumer: ConsumerDetails): ConsumerDetails

    @PostMapping("/consumer/validate")
    fun validateConsumer(@RequestBody consumer: ConsumerDetails): List<String>

    @PostMapping("/consumer/match")
    fun matchConsumer(@RequestBody matchRequest: ConsumerMatchRequest): Long

    @GetMapping("/consumer/{consumerMasterId}/history")
    fun getConsumerHistory(@PathVariable("consumerMasterId") consumerMasterId: Long): ConsumerHistory

    @GetMapping("/address/standardize")
    fun standardizeAddress(@RequestParam("rawAddress") rawAddress: String): Address

    @GetMapping("/generate-id")
    fun generateId(): String

    @GetMapping("/merchant/{mid}/configuration")
    fun getMerchantConfiguration(@PathVariable("mid") mid: Long): MerchantConfiguration

    @GetMapping("/third-party-data")
    fun requestThirdPartyData(@RequestParam("ssn") ssn: String): String

    @GetMapping("/score")
    fun requestScore(@RequestParam("consumerMasterId") consumerMasterId: Long): String

    @PostMapping("/inquiry")
    fun saveInquiry(@RequestBody inquiry: SaveInquiryRequest): Long
}