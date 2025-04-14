package com.work.step2

import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun queue(): Queue {
        return Queue(QUEUE_NAME, true)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): SimpleMessageListenerContainer {
        return SimpleMessageListenerContainer().apply {
            this.connectionFactory = connectionFactory
            setQueueNames(QUEUE_NAME)
            setMessageListener(listenerAdapter)
            acknowledgeMode = AcknowledgeMode.AUTO
        }
    }

    @Bean
    fun listenerAdapter(workQueueTask: WorkQueueConsumer): MessageListenerAdapter {
        return MessageListenerAdapter(workQueueTask, "workQueueTask")
    }

    companion object {
        const val QUEUE_NAME: String = "WorkQueue"
    }
}
