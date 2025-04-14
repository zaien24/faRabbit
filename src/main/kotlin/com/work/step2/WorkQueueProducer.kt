package com.work.step2

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

<<<<<<< HEAD
//@Component
=======
@Component
>>>>>>> d51761e2f81b5d74de6206411b5978b0952eca6c
class WorkQueueProducer(private val rabbitTemplate: RabbitTemplate) {
    private val log = KotlinLogging.logger {}

    fun sendWorkQueue(workQueueMessage: String, duration: Int) {
        val message = "$workQueueMessage|$duration"
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message)
        log.info { "Sent workqueue $message" }
    }
}
