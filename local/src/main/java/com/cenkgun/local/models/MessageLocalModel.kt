package com.cenkgun.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cenkgun.local.models.MessageLocalModel.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME
)
data class MessageLocalModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var text: String,
    var timestamp: Long,
    var user: UserLocalModel
) {
    companion object {
        const val TABLE_NAME = "messages"
    }
}
