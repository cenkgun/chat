package com.cenkgun.local.impl

import com.cenkgun.data.contracts.local.UserLocal
import com.cenkgun.data.models.UserEntity
import com.cenkgun.local.UserDao
import com.cenkgun.local.mappers.UserLocalModelMapper
import javax.inject.Inject

class UserLocalImpl @Inject constructor(
    private val dao: UserDao,
    private val mapper: UserLocalModelMapper
) : UserLocal {
    override suspend fun saveUserList(userList: List<UserEntity>) {
        dao.saveUserList(mapper.mapToModelList(userList))
    }

    override suspend fun getUserByNickname(nickname: String): UserEntity? {
        dao.getUserByNickname(nickname)?.let {
            return mapper.mapToEntity(it)
        }
        return null
    }
}