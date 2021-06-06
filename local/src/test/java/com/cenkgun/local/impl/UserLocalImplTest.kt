package com.cenkgun.local.impl

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cenkgun.data.contracts.local.UserLocal
import com.cenkgun.local.ChatDatabase
import com.cenkgun.local.DummyData.makeUserEntity
import com.cenkgun.local.mappers.UserLocalModelMapper
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserLocalImplTest {

    private lateinit var database: ChatDatabase
    private lateinit var local: UserLocal

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ChatDatabase::class.java
        ).allowMainThreadQueries().build()

        local = UserLocalImpl(
            database.userDao(),
            UserLocalModelMapper()
        )
    }

    @Test
    fun `check that save user saves a user in the database`() = runBlocking {
        val entity = makeUserEntity()
        local.saveUserList(listOf(entity))
        val result = local.getUserByNickname(entity.nickname)
        assertThat(result).isNotNull()
    }
}