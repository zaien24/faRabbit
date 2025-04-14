package com.work.step3
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.FanoutExchange
import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun notificationQueue(): Queue {
        return Queue(QUEUE_NAME, false) // 메시지는 volatile로 설정
    }

    @Bean
    fun fanoutExchange(): FanoutExchange {
        // 메시지를 수신하면 연결된 모든 큐로 브로드캐스트
        return FanoutExchange(FANOUT_EXCHANGE)
    }

    @Bean
    fun bindNotification(notificationQueue: Queue, fanoutExchange: FanoutExchange): Binding {
        // BindingBuilder.bind().to() 를 통해 큐와 익스체인지를 연결
        return BindingBuilder.bind(notificationQueue).to(fanoutExchange)
    }

    companion object {
        const val QUEUE_NAME: String = "notificationQueue"
        const val FANOUT_EXCHANGE: String = "notificationExchange"
    }
}
