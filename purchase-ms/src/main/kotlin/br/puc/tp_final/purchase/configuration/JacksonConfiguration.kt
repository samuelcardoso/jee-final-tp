package br.puc.tp_final.purchase.configuration

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfiguration {

    @Bean
    fun jackson2JsonMessageConverter(): Jackson2JsonMessageConverter {
        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        mapper.registerModule(JavaTimeModule())
        return Jackson2JsonMessageConverter(mapper)
    }
}