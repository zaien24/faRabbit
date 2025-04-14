package com.work.step3

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class NotificationSubscriber(// WebSocket으로 메시지를 전달하기 위한 Spring의 템플릿 클래스.
    private val simpMessagingTemplate: SimpMessagingTemplate,
) {
    private val log = KotlinLogging.logger {}

    @RabbitListener(queues = [RabbitMQConfig.QUEUE_NAME])
    fun subscriber(message: String) {
        // RabbitMQ Queue에서 메시지 수신
        // String message = (String) rabbitTemplate.receiveAndConvert(RabbitMQConfig.QUEUE_NAME);
        log.info { "Received Notification: $message" }
        // convertAndSend 를 통해 특정 경로로 메시지를 전달함
        simpMessagingTemplate.convertAndSend(CLIENT_URL, message) // 클라이언트에 브로드캐스트
    }

    companion object {
        const val CLIENT_URL: String = "/topic/notifications"
    }
}
