package com.cenkgun.local.impl

import com.cenkgun.data.contracts.local.MessageLocal
import com.cenkgun.data.models.MessageEntity
import com.cenkgun.local.MessageDao
import com.cenkgun.local.mappers.MessageLocalModelMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MessageLocalImpl @Inject constructor(
    private val dao: MessageDao,
    private val mapper: MessageLocalModelMapper
) : MessageLocal {

    override fun getFlowMessageList(): Flow<List<MessageEntity>> {
        return flow {
            emitAll(dao.getFlowMessageList().map {
                mapper.mapToEntityList(it)
            })
        }
    }

    override suspend fun getMessageList(): List<MessageEntity> {
        return mapper.mapToEntityList(dao.getMessageList())
    }
    
    override suspend fun saveMessageList(messageEntityList: List<MessageEntity>) {
        dao.saveMessageList(mapper.mapToModelList(messageEntityList))
    }
}