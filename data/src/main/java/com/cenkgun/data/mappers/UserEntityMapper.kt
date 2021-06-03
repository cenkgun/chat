package com.cenkgun.data.mappers

import com.cenkgun.data.mappers.base.EntityMapper
import com.cenkgun.data.models.UserEntity
import com.cenkgun.domain.models.User
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : EntityMapper<UserEntity, User> {

    override fun mapFromEntity(entity: UserEntity): User {
        return entity.run {
            User(id, nickname, avatarURL)
        }
    }

    override fun mapToEntity(domain: User): UserEntity {
        return domain.run {
            UserEntity(id, nickname, avatarURL)
        }
    }
}