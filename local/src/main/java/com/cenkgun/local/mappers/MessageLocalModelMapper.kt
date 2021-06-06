package com.cenkgun.local.mappers

import com.cenkgun.data.models.MessageEntity
import com.cenkgun.local.mappers.base.LocalModelMapper
import com.cenkgun.local.models.MessageLocalModel
import javax.inject.Inject

class MessageLocalModelMapper @Inject constructor(
    private val mapper: UserLocalModelMapper
) : LocalModelMapper<MessageLocalModel, MessageEntity> {

    override fun mapToModel(entity: MessageEntity): MessageLocalModel {
        return entity.run {
            MessageLocalModel(
                text = text,
                timestamp = timestamp,
                user = mapper.mapToModel(user)
            )
        }
    }

    override fun mapToEntity(model: MessageLocalModel): MessageEntity {
        return model.run {
            MessageEntity(
                text = text,
                timestamp = timestamp,
                user = mapper.mapToEntity(user)
            )
        }
    }
}