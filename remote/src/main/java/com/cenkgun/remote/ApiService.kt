package com.cenkgun.remote

import com.cenkgun.remote.models.response.MessagesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("files/chatdata-example.json")
    suspend fun getMessages(): MessagesResponse
}