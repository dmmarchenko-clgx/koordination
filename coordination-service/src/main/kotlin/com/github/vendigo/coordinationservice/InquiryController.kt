package com.github.vendigo.coordinationservice

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class InquiryController(val inquiryService: InquiryService) {

    @PostMapping("/inquiry")
    fun processInquiry(@RequestBody request: InquiryRequest): InquiryResponse {
        return inquiryService.processInquiry(request)
    }
}