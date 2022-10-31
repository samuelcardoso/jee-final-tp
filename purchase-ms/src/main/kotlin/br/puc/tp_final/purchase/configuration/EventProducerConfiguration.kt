package br.puc.tp_final.purchase.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EventProducerConfiguration {

    @Bean
    fun purchaseExchange(): TopicExchange {
        return TopicExchange(RabbitPurchaseConstants.PURCHASE_EXCHANGE, true, false)
    }

    @Bean(RabbitPurchaseConstants.PURCHASE_QUEUE)
    fun purchaseQueue(): Queue {
        return Queue(RabbitPurchaseConstants.PURCHASE_QUEUE, true)
    }

    @Bean
    fun newPurchases(@Qualifier(RabbitPurchaseConstants.PURCHASE_QUEUE) queue: Queue, eventExchange: TopicExchange): Binding {
        return BindingBuilder
            .bind(queue)
            .to(eventExchange)
            .with(RabbitPurchaseConstants.PURCHASE_QUEUE)
    }

    @Bean("rabbitTemplateProducer")
    fun rabbitTemplateProducer(connectionFactory: ConnectionFactory, eventExchange: TopicExchange, jackson2JsonMessageConverter: Jackson2JsonMessageConverter): RabbitTemplate {
        var rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.setExchange(eventExchange.name)
        rabbitTemplate.messageConverter = jackson2JsonMessageConverter
        return rabbitTemplate
    }
}
