package com.cenkgun.remote.models.response

import com.cenkgun.remote.models.MessageNetworkModel

data class MessagesResponse(
    val messages: List<MessageNetworkModel>
)
