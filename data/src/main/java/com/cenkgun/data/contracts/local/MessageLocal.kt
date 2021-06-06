package com.cenkgun.data.contracts.local

import com.cenkgun.data.models.MessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageLocal {

    fun getFlowMessageList(): Flow<List<MessageEntity>>

    suspend fun getMessageList(): List<MessageEntity>

    suspend fun saveMessageList(messageEntityList: List<MessageEntity>)
}