package com.cenkgun.presentation.ui.login.validation

object LoginValidationHelper {
    fun isValidNickname(
        nickname: String
    ): Boolean {
        if (nickname.trim().length > 2) {
            return true
        }
        return false
    }

    fun buttonCanBeEnable(nickname: String): Boolean {
        if (nickname.trim().isEmpty()) {
            return false
        }
        return true
    }
}