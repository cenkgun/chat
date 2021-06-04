package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.models.Session
import com.cenkgun.domain.repositories.LoginRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveLoggedInUserUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val postExecution: PostExecutorThread
) {
    suspend operator fun invoke(session: Session) {
        withContext(postExecution.io) {
            repository.saveLoggedInUser(session)
        }
    }
}