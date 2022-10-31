package br.puc.tp_final.purchase.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.rabbit.core.RabbitTemplate

@Configuration
class EventConsumerConfiguration {

    @Bean("rabbitTemplateConsumer")
    fun rabbitTemplateConsumer(connectionFactory: ConnectionFactory, jackson2MessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2MessageConverter
        return rabbitTemplate
    }
}