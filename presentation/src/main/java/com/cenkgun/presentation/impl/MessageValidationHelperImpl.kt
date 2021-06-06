package com.cenkgun.presentation.impl

import com.cenkgun.domain.helper.MessageValidationHelper
import javax.inject.Inject

class MessageValidationHelperImpl @Inject constructor() : MessageValidationHelper {
    override fun isValidMessage(message: String): Boolean {
        val trimmedMessage = message.trim()
        if (trimmedMessage.isEmpty()) {
            return false
        }
        return true
    }
}