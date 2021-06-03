package com.cenkgun.domain.models

data class Message(
    var text: String,
    var timestamp: Long,
    var user: User
)
