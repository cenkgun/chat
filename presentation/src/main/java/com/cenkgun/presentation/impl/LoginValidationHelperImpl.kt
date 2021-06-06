package com.cenkgun.presentation.impl

import com.cenkgun.domain.helper.LoginValidationHelper
import javax.inject.Inject

class LoginValidationHelperImpl @Inject constructor() : LoginValidationHelper {
    override fun isValidNickname(nickname: String): Boolean {
        if (nickname.trim().length > 2) {
            return true
        }
        return false
    }
}