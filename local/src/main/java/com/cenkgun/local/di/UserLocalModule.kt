package com.cenkgun.local.di

import com.cenkgun.data.contracts.local.UserLocal
import com.cenkgun.local.impl.UserLocalImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserLocalModule {
    @Binds
    abstract fun bindsUserLocal(userCacheImpl: UserLocalImpl): UserLocal
}