package com.cenkgun.presentation.mapper

import com.cenkgun.presentation.mapper.base.ModelMapper
import com.cenkgun.presentation.models.UserModel
import com.cenkgun.domain.models.User
import javax.inject.Inject

class UserModelMapper @Inject constructor() : ModelMapper<User, UserModel> {

    override fun mapToModel(domain: UserModel): User {
        return domain.run {
            User(
                id = id,
                nickname = nickname,
                avatarURL = avatarURL
            )
        }
    }

    override fun mapToDomain(model: User): UserModel {
        return model.run {
            UserModel(
                id = id,
                nickname = nickname,
                avatarURL = avatarURL
            )
        }
    }
}