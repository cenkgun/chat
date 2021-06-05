package com.cenkgun.presentation.mapper

import com.cenkgun.presentation.mapper.base.ModelMapper
import com.cenkgun.presentation.models.SessionModel
import com.cenkgun.domain.models.Session
import javax.inject.Inject

class SessionModelMapper @Inject constructor(
    private val mapper: UserModelMapper
) : ModelMapper<Session, SessionModel> {

    override fun mapToModel(domain: SessionModel): Session {
        return domain.run {
            Session(
                user = mapper.mapToModel(user)
            )
        }
    }

    override fun mapToDomain(model: Session): SessionModel {
        return model.run {
            SessionModel(
                user = mapper.mapToDomain(model.user)
            )
        }
    }
}