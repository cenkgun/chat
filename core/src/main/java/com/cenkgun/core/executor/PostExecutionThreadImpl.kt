package com.cenkgun.core.executor

import com.cenkgun.domain.executor.PostExecutorThread
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PostExecutionThreadImpl @Inject constructor() : PostExecutorThread {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}