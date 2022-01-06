package com.github.vendigo.ttmocks

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TtMocksApplication

fun main(args: Array<String>) {
    runApplication<TtMocksApplication>(*args)
}
