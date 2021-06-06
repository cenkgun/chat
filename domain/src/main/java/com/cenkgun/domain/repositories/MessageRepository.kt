package com.cenkgun.domain.repositories

import com.cenkgun.domain.models.Message
import kotlinx.coroutines.flow.Flow

interface MessageRepository {

    suspend fun saveMessageList(messageList: List<Message>)

    suspend fun getMessageListFromRemote(): List<Message>

    fun getFlowMessageListFromRemote(): Flow<List<Message>>

    fun getFlowMessageListFromDatabase(): Flow<List<Message>>
}