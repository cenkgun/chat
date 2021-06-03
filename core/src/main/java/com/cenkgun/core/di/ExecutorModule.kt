package com.cenkgun.core.di

import com.cenkgun.core.executor.PostExecutionThreadImpl
import com.cenkgun.domain.executor.PostExecutorThread
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class ExecutorModule {

    @Binds
    abstract fun bindPostExecution(
        postExecutionThreadImpl: PostExecutionThreadImpl
    ): PostExecutorThread
}