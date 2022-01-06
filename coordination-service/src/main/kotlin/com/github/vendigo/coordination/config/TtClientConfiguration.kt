package com.github.vendigo.coordination.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.vendigo.coordination.client.TtClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


@Configuration
class TtClientConfiguration {

    @Bean
    fun retrofit(objectMapper: ObjectMapper): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8070")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
    }

    @Bean
    fun ttClient(retrofit: Retrofit): TtClient {
        return retrofit.create(TtClient::class.java)
    }
}