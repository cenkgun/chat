package com.cenkgun.remote.di

import com.cenkgun.data.contracts.remote.MessageRemote
import com.cenkgun.remote.impl.MessageRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MessageRemoteModule {

    @Binds
    abstract fun bindsMessageRemote(messageRemoteImpl: MessageRemoteImpl): MessageRemote
}