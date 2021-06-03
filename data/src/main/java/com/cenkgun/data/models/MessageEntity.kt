package com.cenkgun.data.models

data class MessageEntity(
    var text: String,
    var timestamp: Long,
    var user: UserEntity
)
