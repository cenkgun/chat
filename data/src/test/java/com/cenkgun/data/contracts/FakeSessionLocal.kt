package com.cenkgun.data.contracts

import com.cenkgun.data.contracts.local.SessionLocal
import com.cenkgun.data.models.SessionEntity

class FakeSessionLocal : SessionLocal {

    private val local = LinkedHashMap<String, SessionEntity>()

    override suspend fun saveSession(session: SessionEntity) {
        local[session.user.id] = session
    }

    override suspend fun deleteSession() {
        local.clear()
    }

    override suspend fun getLoggedInUser(): SessionEntity {
        return local.values.first()
    }
}