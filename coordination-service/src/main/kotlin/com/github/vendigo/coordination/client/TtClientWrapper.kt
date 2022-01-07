package com.github.vendigo.coordination.client

import kotlinx.coroutines.Deferred
import org.springframework.stereotype.Service

@Service
class TtClientWrapper(val ttClient: TtClient) {

    suspend fun generateId(): Deferred<String> {
        TODO("Implement")
    }
}