package com.cenkgun.data.impl

import com.cenkgun.data.contracts.local.MessageLocal
import com.cenkgun.data.contracts.remote.MessageRemote
import com.cenkgun.data.mappers.MessageEntityMapper
import com.cenkgun.domain.models.Message
import com.cenkgun.domain.repositories.MessageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageRepositoryImpl @Inject constructor(
    private val messageLocal: MessageLocal,
    private val messageRemote: MessageRemote,
    private val mapper: MessageEntityMapper
) : MessageRepository {

    override suspend fun saveMessageList(messageList: List<Message>) {
        messageLocal.saveMessageList(mapper.mapFromDomainList(messageList))
    }

    override suspend fun getMessageListFromRemote(): List<Message> {
        return mapper.mapFromEntityList(messageLocal.getMessageList())
    }

    override fun getFlowMessageListFromRemote(): Flow<List<Message>> {
        return flow {
            emitAll(messageRemote.getMessageList().map {
                mapper.mapFromEntityList(it)
            })
        }
    }

    override fun getFlowMessageListFromDatabase(): Flow<List<Message>> {
        return flow {
            emitAll(messageLocal.getFlowMessageList().map {
                mapper.mapFromEntityList(it)
            })
        }
    }
}