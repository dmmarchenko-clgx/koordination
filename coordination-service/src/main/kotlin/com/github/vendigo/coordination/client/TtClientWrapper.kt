package com.github.vendigo.coordination.client

import com.github.vendigo.coordination.model.Address
import com.github.vendigo.coordination.model.ConsumerDetails
import kotlinx.coroutines.*
import org.springframework.stereotype.Service

@Service
@Suppress("BlockingMethodInNonBlockingContext")
@SuppressWarnings("kotlin:S6310")
class TtClientWrapper(val ttClient: TtClient) {

    suspend fun normalizeConsumer(consumer: ConsumerDetails): Deferred<ConsumerDetails> {
        return httpCall {
            ttClient.normalizeConsumer(consumer)
        }
    }

    suspend fun standardizeAddress(rawAddress: String): Deferred<Address> {
        return httpCall {
            ttClient.standardizeAddress(rawAddress)
        }
    }

    suspend fun generateId(): Deferred<String> {
        return httpCall {
            ttClient.generateId()
        }
    }

    suspend fun <T> httpCall(block: suspend CoroutineScope.() -> T): Deferred<T> {
        return withContext(Dispatchers.IO) {
            async(start = CoroutineStart.DEFAULT) {
                block()
            }
        }
    }
}