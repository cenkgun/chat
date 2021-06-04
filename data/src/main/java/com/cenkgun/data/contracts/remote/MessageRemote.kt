package com.cenkgun.data.contracts.remote

import com.cenkgun.data.models.MessageEntity
import kotlinx.coroutines.flow.Flow

interface MessageRemote {
    fun getMessageList(): Flow<List<MessageEntity>>
}