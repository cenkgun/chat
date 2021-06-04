package com.cenkgun.remote.mapper

import com.cenkgun.data.models.MessageEntity
import com.cenkgun.remote.extensions.toMillisecond
import com.cenkgun.remote.mapper.base.RemoteModelMapper
import com.cenkgun.remote.models.MessageNetworkModel
import javax.inject.Inject

class MessageNetworkMapper @Inject constructor(
    private val mapper: UserNetworkMapper
) : RemoteModelMapper<MessageNetworkModel, MessageEntity> {

    override fun mapFromModel(model: MessageNetworkModel): MessageEntity {
        return MessageEntity(
            model.text,
            model.timestamp.toMillisecond(),
            mapper.mapFromModel(model.user)
        )
    }
}