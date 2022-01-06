package com.github.vendigo.coordinationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CoordinationServiceApplication

fun main(args: Array<String>) {
    runApplication<CoordinationServiceApplication>(*args)
}
