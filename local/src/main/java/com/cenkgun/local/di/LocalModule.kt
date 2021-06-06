package com.cenkgun.local.di

import android.content.Context
import com.cenkgun.local.MessageDao
import com.cenkgun.local.ChatDatabase
import com.cenkgun.local.SessionDao
import com.cenkgun.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @[Provides Singleton]
    fun providesDatabase(@ApplicationContext context: Context): ChatDatabase {
        return ChatDatabase.getInstance(context)
    }

    @[Provides Singleton]
    fun providesUserDao(database: ChatDatabase): UserDao {
        return database.userDao()
    }

    @[Provides Singleton]
    fun providesSessionDao(database: ChatDatabase): SessionDao {
        return database.sessionDao()
    }

    @[Provides Singleton]
    fun providesMessageDao(database: ChatDatabase): MessageDao {
        return database.messageDao()
    }
}