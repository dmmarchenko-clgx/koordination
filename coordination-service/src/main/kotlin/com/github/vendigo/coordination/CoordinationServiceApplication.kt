package com.github.vendigo.coordination

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoordinationServiceApplication

fun main(args: Array<String>) {
    runApplication<CoordinationServiceApplication>(*args)
}
