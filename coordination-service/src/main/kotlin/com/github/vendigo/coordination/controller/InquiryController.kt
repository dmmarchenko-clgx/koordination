package com.github.vendigo.coordination.controller

import com.github.vendigo.coordination.client.TtClient
import com.github.vendigo.coordination.model.Address
import com.github.vendigo.coordination.model.ConsumerDetails
import com.github.vendigo.coordination.model.InquiryRequest
import com.github.vendigo.coordination.model.InquiryResponse
import com.github.vendigo.coordination.service.InquiryService
import org.springframework.web.bind.annotation.*

@RestController
class InquiryController(val inquiryService: InquiryService, val ttClient: TtClient) {

    @PostMapping("/inquiry")
    fun processInquiry(@RequestBody request: InquiryRequest): InquiryResponse {
        return inquiryService.processInquiry(request)
    }

    @GetMapping("/generate-id")
    suspend fun generateId(): String {
        val generatedId = ttClient.generateId()
        println("Generated id: $generatedId")
        return generatedId
    }

    @GetMapping("/standardize-address")
    suspend fun generateId(@RequestParam("rawAddress") rawAddress: String): Address {
        val address = ttClient.standardizeAddress(rawAddress)
        println("Address: $address")
        return address
    }

    @PostMapping("/normalize-consumer")
    suspend fun normalizeConsumer(@RequestBody consumerDetails: ConsumerDetails): ConsumerDetails {
        val normalized = ttClient.normalizeConsumer(consumerDetails)
        println("Normalized: $normalized")
        return normalized
    }
}