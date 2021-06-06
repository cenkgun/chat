package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.models.Message
import com.cenkgun.domain.repositories.MessageRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveMessageListUseCase @Inject constructor(
    private val repository: MessageRepository,
    private val postExecution: PostExecutorThread
) {
    suspend operator fun invoke(messageList: List<Message>) {
        withContext(postExecution.io) {
            repository.saveMessageList(messageList)
        }
    }
}