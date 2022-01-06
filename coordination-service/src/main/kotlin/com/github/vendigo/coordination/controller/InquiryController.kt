package com.github.vendigo.coordination.controller

import com.github.vendigo.coordination.model.InquiryRequest
import com.github.vendigo.coordination.model.InquiryResponse
import com.github.vendigo.coordination.service.InquiryService
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