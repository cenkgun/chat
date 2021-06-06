package com.cenkgun.remote.models

data class MessageNetworkModel(
    val id: String,
    val text: String,
    val timestamp: Long,
    val user: UserNetworkModel
)