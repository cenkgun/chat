package com.cenkgun.domain.usecases

import com.cenkgun.domain.executor.PostExecutorThread
import com.cenkgun.domain.models.Message
import com.cenkgun.domain.repositories.MessageRepository
import com.cenkgun.domain.usecases.base.FlowUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMessageListFromRemoteUseCase @Inject constructor(
    private val repository: MessageRepository,
    private val postExecution: PostExecutorThread
) : FlowUseCase<Nothing, List<Message>>() {

    override val dispatcher: CoroutineDispatcher
        get() = postExecution.io

    override fun execute(params: Nothing?): Flow<List<Message>> {
        return repository.getFlowMessageListFromRemote()
    }
}