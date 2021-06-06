package com.cenkgun.domain.usecases

import com.cenkgun.domain.DummyData.makeUser
import com.cenkgun.domain.fakes.FakeRepository
import com.cenkgun.domain.fakes.TestPostExecutionThread
import com.cenkgun.domain.models.User
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveUserListUseCaseTest {

    private val repository = FakeRepository()
    private val saveUserListUseCase = SaveUserListUseCase(repository, TestPostExecutionThread())

    @Test
    fun `check that save user`() = runBlockingTest {
        val user = makeUser()
        repository.saveUserList(listOf(user))
        saveUserListUseCase(listOf(user))
        val result: User? = repository.getUserByNickname(user.nickname)
        Truth.assertThat(result).isNotNull()
    }
}