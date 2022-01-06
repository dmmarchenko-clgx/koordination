package com.github.vendigo.coordination.client

import com.github.vendigo.coordination.model.Address
import com.github.vendigo.coordination.model.ConsumerDetails
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TtClient {

    @POST("/consumer/normalize")
    suspend fun normalizeConsumer(@Body consumer: ConsumerDetails): ConsumerDetails
/*
    @PostMapping("/consumer/validate")
    fun validateConsumer(@RequestBody consumer: ConsumerDetails): List<String>

    @PostMapping("/consumer/match")
    fun matchConsumer(@RequestBody matchRequest: ConsumerMatchRequest): Long

    @GetMapping("/consumer/{consumerMasterId}/history")
    fun getConsumerHistory(@PathVariable("consumerMasterId") consumerMasterId: Long): ConsumerHistory*/

    @GET("/address/standardize")
    suspend fun standardizeAddress(@Query("rawAddress") rawAddress: String): Address

    @GET("/generate-id")
    suspend fun generateId(): String

    /*@GetMapping("/merchant/{mid}/configuration")
    fun getMerchantConfiguration(@PathVariable("mid") mid: Long): MerchantConfiguration

    @GetMapping("/third-party-data")
    fun requestThirdPartyData(@RequestParam("ssn") ssn: String): String

    @GetMapping("/score")
    fun requestScore(@RequestParam("consumerMasterId") consumerMasterId: Long): String

    @PostMapping("/inquiry")
    fun saveInquiry(@RequestBody inquiry: SaveInquiryRequest): Long*/
}