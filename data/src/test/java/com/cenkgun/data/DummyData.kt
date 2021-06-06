package com.cenkgun.data

import com.cenkgun.domain.models.User

object DummyData {
    fun makeUser(): User {
        return User("1", "gun", "url")
    }
}