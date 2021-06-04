package com.cenkgun.domain.repositories

import com.cenkgun.domain.models.Session
import com.cenkgun.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun saveUserList(userList: List<User>)

    suspend fun deleteLoggedInUser()

    suspend fun saveLoggedInUser(session: Session)

    suspend fun getUserByNickname(nickname: String): User?

    fun getActiveSession(): Flow<Session>
}