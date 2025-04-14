package com.work.step3

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class NotificationPublisher(private val rabbitTemplate: RabbitTemplate) {
    private val log = KotlinLogging.logger {}

    fun publish(message: String) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "", message) // Fanout에서 routing key는 무시됨
        log.info { "[#] Published Notification: $message" }
    }
}
