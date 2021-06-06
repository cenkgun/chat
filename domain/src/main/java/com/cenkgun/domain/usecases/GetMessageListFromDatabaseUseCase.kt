package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.repositories.MessageRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetMessageListFromDatabaseUseCase @Inject constructor(
    private val repository: MessageRepository,
    private val postExecution: PostExecutorThread
) {
    suspend operator fun invoke() = withContext(postExecution.io) {
        repository.getMessageListFromRemote()
    }
}