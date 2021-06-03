package com.cenkgun.local.mappers

import com.cenkgun.data.models.SessionEntity
import com.cenkgun.local.mappers.base.LocalModelMapper
import com.cenkgun.local.models.SessionLocalModel
import javax.inject.Inject

class SessionLocalModelMapper @Inject constructor(
    private val mapper: UserLocalModelMapper
) : LocalModelMapper<SessionLocalModel, SessionEntity> {
    override fun mapToModel(entity: SessionEntity): SessionLocalModel {
        return entity.run {
            SessionLocalModel(user = mapper.mapToModel(user))
        }
    }

    override fun mapToEntity(model: SessionLocalModel): SessionEntity {
        return model.run {
            SessionEntity(user = mapper.mapToEntity(user))
        }
    }
}