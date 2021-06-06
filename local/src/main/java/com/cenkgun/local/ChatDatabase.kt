package com.cenkgun.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cenkgun.local.models.MessageLocalModel
import com.cenkgun.local.models.SessionLocalModel
import com.cenkgun.local.models.UserLocalModel


@Database(
    entities = [
        UserLocalModel::class,
        SessionLocalModel::class,
        MessageLocalModel::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ChatDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun messageDao(): MessageDao

    abstract fun sessionDao(): SessionDao

    companion object {
        private const val DATABASE_NAME = "chatDb"

        @Volatile
        private var INSTANCE: ChatDatabase? = null

        fun getInstance(context: Context): ChatDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ChatDatabase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()
    }
}