package com.cenkgun.local

import com.cenkgun.data.models.UserEntity
import com.cenkgun.local.models.UserLocalModel

object DummyData {

    fun makeUserEntity(): UserEntity {
        return UserEntity("1", "Cenk", "https://picsum.photos/200")
    }

    fun makeUser(): UserLocalModel {
        return UserLocalModel("1", "Cenk", "https://picsum.photos/200")
    }
}