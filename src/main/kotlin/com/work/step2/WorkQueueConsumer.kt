package com.work.step2

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

//@Component
class WorkQueueConsumer {
    private val log = KotlinLogging.logger {}

    fun workQueueTask(message: String) {
        val messageParts: Array<String> = message.split("\\|".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val originMessage = messageParts[0]
        val duration = messageParts[1].trim { it <= ' ' }.toInt()
        log.info { "# Consumer Received: $originMessage (duration: $duration ms)" }

        try {
            val seconds = duration / 1000 // duration을 초 단위로 변환
            for (i in 0..seconds) {
                Thread.sleep(1000) // 1초 대기
                print(".") // 진행 상태 출력
            }
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
        log.info { "[X] $originMessage Completed!!" }
    }
}
