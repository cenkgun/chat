package com.cenkgun.domain.fakes

import com.cenkgun.domain.models.Session
import com.cenkgun.domain.models.User
import com.cenkgun.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRepository : LoginRepository {

    private val userLocal = LinkedHashMap<String, User>()
    private val sessionLocal = LinkedHashMap<String, Session>()

    override suspend fun saveUserList(userList: List<User>) {
        userList.forEach {
            userLocal[it.nickname] = it
        }
    }

    override suspend fun deleteLoggedInUser() {
        sessionLocal.clear()
    }

    override suspend fun saveLoggedInUser(session: Session) {
        sessionLocal[session.user.id] = session
    }

    override suspend fun getUserByNickname(nickname: String): User? {
        return userLocal[nickname]
    }

    override fun getActiveSession(): Flow<Session> {
        return flowOf(sessionLocal.values.first())
    }
}