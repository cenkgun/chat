package com.cenkgun.presentation.di

import com.cenkgun.domain.helper.MessageValidationHelper
import com.cenkgun.presentation.impl.MessageValidationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MessageValidationHelperModule {

    @Binds
    abstract fun bindsMessageValidationHelper(
        messageValidationHelperImpl: MessageValidationHelperImpl
    ): MessageValidationHelper
}