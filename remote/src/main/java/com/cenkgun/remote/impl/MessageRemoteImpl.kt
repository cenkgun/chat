package com.cenkgun.remote.impl

import com.cenkgun.data.contracts.remote.MessageRemote
import com.cenkgun.data.models.MessageEntity
import com.cenkgun.remote.ApiService
import com.cenkgun.remote.mapper.MessageNetworkMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessageRemoteImpl @Inject constructor(
    private val mapper: MessageNetworkMapper,
    private val service: ApiService
) : MessageRemote {
    override fun getMessageList(): Flow<List<MessageEntity>> {
        return flow {
            val apiResponse = service.getMessages().messages
            if (apiResponse.isNotEmpty()) {
                emit(apiResponse.map {
                    mapper.mapFromModel(it)
                })
            }
        }
    }
}