package com.cenkgun.local.impl

import com.cenkgun.data.contracts.local.SessionLocal
import com.cenkgun.data.models.SessionEntity
import com.cenkgun.local.SessionDao
import com.cenkgun.local.mappers.SessionLocalModelMapper
import javax.inject.Inject

class SessionLocalImpl @Inject constructor(
    private val dao: SessionDao,
    private val mapper: SessionLocalModelMapper
) : SessionLocal {
    override suspend fun saveSession(session: SessionEntity) {
        dao.saveSession(mapper.mapToModel(session))
    }

    override suspend fun deleteSession() {
        dao.deleteSession()
    }

    override suspend fun getLoggedInUser(): SessionEntity? {
        val activeUser = dao.getLoggedInUser()
        activeUser?.let {
            return mapper.mapToEntity(it)
        }
        return null
    }
}