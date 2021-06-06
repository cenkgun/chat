package com.cenkgun.domain

import com.cenkgun.domain.models.User

object DummyData {
    fun makeUser(): User {
        return User("54", "cenkgun", "url")
    }
}