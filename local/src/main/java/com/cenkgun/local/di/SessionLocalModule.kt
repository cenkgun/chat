package com.cenkgun.local.di

import com.cenkgun.data.contracts.local.SessionLocal
import com.cenkgun.local.impl.SessionLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionLocalModule {
    @Binds
    abstract fun bindsSessionLocal(sessionLocalImpl: SessionLocalImpl): SessionLocal
}