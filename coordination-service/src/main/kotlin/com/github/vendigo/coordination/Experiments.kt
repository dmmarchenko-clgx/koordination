package com.github.vendigo.coordination

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() {

    val log = LoggerFactory.getLogger(CoordinationServiceApplication::class.java)!!

    runBlocking {
        val first = async {
            log.info("Starting first")
            delay(300)
            log.info("Finishing first")
            1
        }

        val second = async {
            log.info("Starting second")
            delay(100)
            log.info("Finishing second")
            2
        }.await()

        log.info("Result: ${first.await() + second}")
    }
    Thread.sleep(500)
}