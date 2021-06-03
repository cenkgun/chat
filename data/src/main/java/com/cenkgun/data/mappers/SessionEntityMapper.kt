package com.cenkgun.data.mappers

import com.cenkgun.data.mappers.base.EntityMapper
import com.cenkgun.data.models.SessionEntity
import com.cenkgun.domain.models.Session
import javax.inject.Inject

class SessionEntityMapper @Inject constructor(
    private val mapper: UserEntityMapper
) : EntityMapper<SessionEntity, Session> {
    override fun mapFromEntity(entity: SessionEntity): Session {
        return entity.run {
            Session(
                user = mapper.mapFromEntity(user)
            )
        }
    }

    override fun mapToEntity(domain: Session): SessionEntity {
        return domain.run {
            SessionEntity(
                user = mapper.mapToEntity(user)
            )
        }
    }
}