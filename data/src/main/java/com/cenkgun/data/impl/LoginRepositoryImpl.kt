package com.cenkgun.data.impl

import com.cenkgun.data.contracts.local.SessionLocal
import com.cenkgun.data.contracts.local.UserLocal
import com.cenkgun.data.mappers.SessionEntityMapper
import com.cenkgun.data.mappers.UserEntityMapper
import com.cenkgun.domain.models.Session
import com.cenkgun.domain.models.User
import com.cenkgun.domain.repositories.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val userLocal: UserLocal,
    private val sessionLocal: SessionLocal,
    private val userEntityMapper: UserEntityMapper,
    private val sessionEntityMapper: SessionEntityMapper
) : LoginRepository {
    override suspend fun saveUserList(userList: List<User>) {
        userLocal.saveUserList(userEntityMapper.mapFromDomainList(userList))
    }

    override suspend fun deleteLoggedInUser() {
        sessionLocal.deleteSession()
    }

    override suspend fun saveLoggedInUser(session: Session) {
        sessionLocal.saveSession(sessionEntityMapper.mapToEntity(session))
    }

    override suspend fun getUserByNickname(nickname: String): User? {
        userLocal.getUserByNickname(nickname)?.let {
            return userEntityMapper.mapFromEntity(it)
        }
        return null
    }

    override fun getActiveSession(): Flow<Session> {
        return flow {
            sessionLocal.getLoggedInUser()?.let {
                emit(sessionEntityMapper.mapFromEntity(it))
            }
        }
    }
}