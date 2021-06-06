package com.cenkgun.presentation.di

import com.cenkgun.domain.providers.ResourceProvider
import com.cenkgun.presentation.impl.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ResourceProviderModule {

    @Binds
    abstract fun bindsResourceProvider(
        resourceProviderImpl: ResourceProviderImpl
    ): ResourceProvider
}