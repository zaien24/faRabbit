package com.work.step3

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller

@Controller
class StompController(private val messagingTemplate: SimpMessagingTemplate) {
    private val log = KotlinLogging.logger {}

    @MessageMapping("/send")
    fun sendMessage(notificationMessage: NotificationMessage) {
        // 수신된 메시지를 브로드캐스팅
        val message = notificationMessage.message

        log.info { "[#] message = $message" }
        // 클라이언트에 메시지 브로드캐스트
        messagingTemplate.convertAndSend("/topic/notifications", message)
    }
}
