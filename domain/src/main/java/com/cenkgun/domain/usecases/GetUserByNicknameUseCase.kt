package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.models.User
import com.cenkgun.domain.repositories.LoginRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetUserByNicknameUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val postExecution: PostExecutorThread
) {
    suspend operator fun invoke(nickname: String): User? {
        return withContext(postExecution.io) {
            repository.getUserByNickname(nickname)
        }
    }
}