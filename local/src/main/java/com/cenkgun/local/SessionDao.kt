package com.cenkgun.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cenkgun.local.models.SessionLocalModel
import com.cenkgun.local.models.SessionLocalModel.Companion.TABLE_NAME

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSession(session: SessionLocalModel)

    @Query("DELETE FROM $TABLE_NAME")
    suspend fun deleteSession()

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getLoggedInUser(): SessionLocalModel?
}