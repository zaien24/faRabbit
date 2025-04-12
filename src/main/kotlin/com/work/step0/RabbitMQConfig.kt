package com.work.step0

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig(val queueName: String = "helloqueue") {

    /**
     * RabbitMQ에서 사용할 큐를 정의합니다.
     * 첫 번째 파라미터는 큐 이름, 두 번째 파라미터는 큐의 지속성(durability)을 나타냅니다.
     * false로 설정하면 RabbitMQ 서버 재시작 시 큐가 사라집니다.
     */
    @Bean
    fun queue(): Queue {
        return Queue(queueName, false)
    }

    /**
     * RabbitMQ 서버와 통신하기 위한 템플릿을 생성합니다.
     * 메시지 발행(publishing)에 사용됩니다.
     */
    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }

    /**
     * 메시지 리스너 컨테이너를 설정합니다.
     * 큐에서 메시지를 소비하기 위한 리스너를 등록하고 관리합니다.
     */
    @Bean
    fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): SimpleMessageListenerContainer {
        return SimpleMessageListenerContainer().apply {
            this.connectionFactory = connectionFactory
            setQueueNames(queueName)
            setMessageListener(listenerAdapter)
        }
    }

    /**
     * 수신된 메시지를 처리할 리스너 어댑터를 생성합니다.
     * Receiver 객체의 receiveMessage 메서드가 메시지 처리 시 호출됩니다.
     */
    @Bean
    fun listenerAdapter(receiver: Receiver): MessageListenerAdapter {
        return MessageListenerAdapter(receiver, "receiveMessage")
    }
}
