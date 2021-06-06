package com.cenkgun.local.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cenkgun.local.models.UserLocalModel.Companion.INDEX_VALUE
import com.cenkgun.local.models.UserLocalModel.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = [INDEX_VALUE], unique = true)]
)
data class UserLocalModel(
    @PrimaryKey
    var id: String,
    var nickname: String,
    var avatarURL: String?
) {
    companion object {
        const val TABLE_NAME = "user"
        const val INDEX_VALUE = "nickname"
    }
}