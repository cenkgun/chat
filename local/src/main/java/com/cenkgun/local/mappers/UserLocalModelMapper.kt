package com.cenkgun.local.mappers

import com.cenkgun.data.models.UserEntity
import com.cenkgun.local.mappers.base.LocalModelMapper
import com.cenkgun.local.models.UserLocalModel
import javax.inject.Inject

class UserLocalModelMapper @Inject constructor() : LocalModelMapper<UserLocalModel, UserEntity> {

    override fun mapToModel(entity: UserEntity): UserLocalModel {
        return entity.run {
            UserLocalModel(id, nickname, avatarURL)
        }
    }

    override fun mapToEntity(model: UserLocalModel): UserEntity {
        return model.run {
            UserEntity(id, nickname, avatarURL)
        }
    }
}