package com.github.vendigo.coordination.client


interface TtClientRetrofit {

    //@GET("/generate-id")
    suspend fun generateId(): String
}