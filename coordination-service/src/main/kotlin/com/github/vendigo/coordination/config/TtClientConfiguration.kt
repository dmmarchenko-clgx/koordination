package com.github.vendigo.coordination.config

import com.github.vendigo.coordination.client.TtClient
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit


@Configuration
@ExperimentalSerializationApi
class TtClientConfiguration {

    @Bean
    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8070")
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            //.addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Bean
    fun ttClient(retrofit: Retrofit): TtClient {
        return retrofit.create(TtClient::class.java)
    }
}