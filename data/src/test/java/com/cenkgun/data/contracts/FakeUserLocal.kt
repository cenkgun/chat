package com.cenkgun.data.contracts

import com.cenkgun.data.contracts.local.UserLocal
import com.cenkgun.data.models.UserEntity

class FakeUserLocal : UserLocal {

    private val local = LinkedHashMap<String, UserEntity>()

    override suspend fun saveUserList(userList: List<UserEntity>) {
        userList.forEach {
            local[it.nickname] = it
        }
    }

    override suspend fun getUserByNickname(nickname: String): UserEntity? {
        return local[nickname]
    }
}