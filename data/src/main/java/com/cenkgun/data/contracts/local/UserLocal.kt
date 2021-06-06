package com.cenkgun.data.contracts.local

import com.cenkgun.data.models.UserEntity

interface UserLocal {
    suspend fun saveUserList(userList: List<UserEntity>)

    suspend fun getUserByNickname(nickname: String): UserEntity?
}