package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.models.Session
import com.cenkgun.domain.repositories.LoginRepository
import com.cenkgun.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLoggedInUserUseCase @Inject constructor(
    private val repository: LoginRepository,
    private val postExecution: PostExecutorThread
) : FlowUseCase<Nothing, Session>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecution.io

    override fun execute(params: Nothing?): Flow<Session> {
        return repository.getActiveSession()
    }
}