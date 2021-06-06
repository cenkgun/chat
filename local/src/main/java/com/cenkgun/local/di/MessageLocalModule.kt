package com.cenkgun.local.di

import com.cenkgun.data.contracts.local.MessageLocal
import com.cenkgun.local.impl.MessageLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MessageLocalModule {
    @Binds
    abstract fun bindsMessageLocal(messageLocalImpl: MessageLocalImpl): MessageLocal
}