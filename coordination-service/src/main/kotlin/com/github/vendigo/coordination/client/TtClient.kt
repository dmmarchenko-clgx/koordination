package com.github.vendigo.coordination.client

import com.github.vendigo.coordination.model.*
import retrofit2.http.*

interface TtClient {

    @POST("/consumer/normalize")
    suspend fun normalizeConsumer(@Body consumer: ConsumerDetails): ConsumerDetails

    @POST("/consumer/validate")
    suspend fun validateConsumer(@Body consumer: ConsumerDetails): List<String>

    @POST("/consumer/match")
    suspend fun matchConsumer(@Body matchRequest: ConsumerMatchRequest): Long

    @GET("/consumer/{consumerMasterId}/history")
    suspend fun getConsumerHistory(@Path("consumerMasterId") consumerMasterId: Long): ConsumerHistory

    @GET("/address/standardize")
    suspend fun standardizeAddress(@Query("rawAddress") rawAddress: String): Address

    @GET("/generate-id")
    suspend fun generateId(): String

    @GET("/merchant/{mid}/configuration")
    suspend fun getMerchantConfiguration(@Path("mid") mid: Long): MerchantConfiguration

    @GET("/third-party-data")
    suspend fun requestThirdPartyData(@Query("ssn") ssn: String): String

    @GET("/score")
    suspend fun requestScore(@Query("consumerId") consumerId: Long): String

    @POST("/inquiry")
    suspend fun saveInquiry(@Body inquiry: SaveInquiryRequest): Long
}