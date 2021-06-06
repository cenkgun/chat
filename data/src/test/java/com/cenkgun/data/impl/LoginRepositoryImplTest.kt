package com.cenkgun.data.impl

import com.cenkgun.data.DummyData.makeUser
import com.cenkgun.data.contracts.FakeSessionLocal
import com.cenkgun.data.contracts.FakeUserLocal
import com.cenkgun.data.mappers.SessionEntityMapper
import com.cenkgun.data.mappers.UserEntityMapper
import com.cenkgun.domain.models.User
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginRepositoryImplTest {

    private val userLocal = FakeUserLocal()
    private val sessionLocal = FakeSessionLocal()
    private val userMapper = UserEntityMapper()
    private val sessionMapper = SessionEntityMapper(userMapper)

    private val repository = LoginRepositoryImpl(
        userLocal,
        sessionLocal,
        userMapper,
        sessionMapper
    )

    @Test
    fun `check get user`() = runBlocking {
        val user = makeUser()
        repository.saveUserList(listOf(user))
        val result: User? = repository.getUserByNickname(user.nickname)
        Truth.assertThat(result).isNotNull()
    }
}