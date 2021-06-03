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

}