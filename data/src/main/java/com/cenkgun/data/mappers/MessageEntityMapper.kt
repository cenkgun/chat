package com.cenkgun.data.mappers

import com.cenkgun.data.mappers.base.EntityMapper
import com.cenkgun.data.models.MessageEntity
import com.cenkgun.domain.models.Message
import javax.inject.Inject

class MessageEntityMapper @Inject constructor(
    private val mapper: UserEntityMapper
) : EntityMapper<MessageEntity, Message> {

    override fun mapFromEntity(entity: MessageEntity): Message {
        return entity.run {
            Message(
                text,
                timestamp,
                mapper.mapFromEntity(user)
            )
        }
    }

    override fun mapToEntity(domain: Message): MessageEntity {
        return domain.run {
            MessageEntity(
                text,
                timestamp,
                mapper.mapToEntity(user)
            )
        }
    }
}