package com.cenkgun.remote.mapper

import com.cenkgun.data.models.UserEntity
import com.cenkgun.remote.mapper.base.RemoteModelMapper
import com.cenkgun.remote.models.UserNetworkModel
import javax.inject.Inject

class UserNetworkMapper @Inject constructor() : RemoteModelMapper<UserNetworkModel, UserEntity> {
    override fun mapFromModel(model: UserNetworkModel): UserEntity {
        return UserEntity(
            model.id,
            model.nickname,
            model.avatarURL
        )
    }
}