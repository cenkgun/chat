package com.cenkgun.core.di

import com.cenkgun.data.impl.MessageRepositoryImpl
import com.cenkgun.data.impl.LoginRepositoryImpl
import com.cenkgun.domain.repositories.MessageRepository
import com.cenkgun.domain.repositories.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMessageRepository(
        messageRepositoryImpl: MessageRepositoryImpl
    ): MessageRepository

    @Binds
    abstract fun bindsLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository
}