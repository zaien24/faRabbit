package com.work.step0


import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class Sender(
    private val rabbitTemplate: RabbitTemplate,
    rabbitMQConfig: RabbitMQConfig
) {
    private val log = KotlinLogging.logger {}
    private val queueName = rabbitMQConfig.queueName

    fun send(message: String) {
        val safeMessage = message
        rabbitTemplate.convertAndSend(queueName, safeMessage)
        log.info { "[#] Sent: $safeMessage" }
    }
}
