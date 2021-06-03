package com.cenkgun.domain.executor

import kotlinx.coroutines.CoroutineDispatcher

interface PostExecutorThread {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}