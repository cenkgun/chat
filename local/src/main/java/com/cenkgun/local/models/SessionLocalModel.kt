package com.cenkgun.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = SessionLocalModel.TABLE_NAME
)
data class SessionLocalModel(
    @PrimaryKey
    var id: Int = 1,
    var user: UserLocalModel
) {
    companion object {
        const val TABLE_NAME = "session"
    }
}
