package com.cenkgun.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cenkgun.local.models.UserLocalModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserList(userList: List<UserLocalModel>)

    @Query("SELECT * FROM ${UserLocalModel.TABLE_NAME} WHERE nickname=:nickname")
    suspend fun getUserByNickname(nickname: String): UserLocalModel?
}