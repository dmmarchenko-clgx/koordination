package com.github.vendigo.coordination.service

import com.github.vendigo.coordination.flow.FlowContext
import com.github.vendigo.coordination.model.InquiryRequest
import com.github.vendigo.coordination.model.InquiryResponse
import com.github.vendigo.coordination.resolver.Resolvers
import kotlinx.coroutines.coroutineScope
import org.springframework.stereotype.Service

@Service
class InquiryService(val resolvers: Resolvers) {

    suspend fun processInquiry(request: InquiryRequest): InquiryResponse = coroutineScope {
        val context = FlowContext(request, this, resolvers)
        context.inquiryResponse.await()
    }
}