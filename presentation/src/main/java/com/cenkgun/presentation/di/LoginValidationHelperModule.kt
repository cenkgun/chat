package com.cenkgun.presentation.di

import com.cenkgun.domain.helper.LoginValidationHelper
import com.cenkgun.presentation.impl.LoginValidationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginValidationHelperModule {

    @Binds
    abstract fun bindsLoginValidationHelper(
        loginValidationHelperImpl: LoginValidationHelperImpl
    ): LoginValidationHelper
}