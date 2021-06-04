package com.cenkgun.data.contracts.local

import com.cenkgun.data.models.SessionEntity

interface SessionLocal {
    suspend fun saveSession(session: SessionEntity)

    suspend fun deleteSession()

    suspend fun getLoggedInUser(): SessionEntity?
}