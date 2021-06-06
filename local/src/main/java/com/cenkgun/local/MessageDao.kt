package com.cenkgun.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cenkgun.local.models.MessageLocalModel
import com.cenkgun.local.models.MessageLocalModel.Companion.TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMessageList(messageList: List<MessageLocalModel>)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getMessageList(): List<MessageLocalModel>

    @Query("SELECT * FROM $TABLE_NAME ORDER BY timestamp DESC")
    fun getFlowMessageList(): Flow<List<MessageLocalModel>>
}