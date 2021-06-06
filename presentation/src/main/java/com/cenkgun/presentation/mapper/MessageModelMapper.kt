package com.cenkgun.presentation.mapper

import com.cenkgun.domain.models.Message
import com.cenkgun.presentation.mapper.base.ModelMapper
import com.cenkgun.presentation.models.MessageModel
import javax.inject.Inject

class MessageModelMapper @Inject constructor(
    private val mapper: UserModelMapper
) : ModelMapper<MessageModel, Message> {
    override fun mapToModel(domain: Message): MessageModel {
        return domain.run {
            MessageModel(
                text = text,
                timestamp = timestamp,
                user = mapper.mapToDomain(user)
            )
        }
    }

    override fun mapToDomain(model: MessageModel): Message {
        return model.run {
            Message(
                text = text,
                timestamp = timestamp,
                user = mapper.mapToModel(user)
            )
        }
    }
}