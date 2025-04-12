package com.work

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FaRabbitApplication

fun main(args: Array<String>) {
    runApplication<FaRabbitApplication>(*args)
}
