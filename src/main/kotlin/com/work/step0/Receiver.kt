package com.work.step0

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component


@Component
class Receiver {
    private val log = KotlinLogging.logger {}

    fun receiveMessage(message: String) {
        log.info { "[#] Received: $message" }
    }
}
