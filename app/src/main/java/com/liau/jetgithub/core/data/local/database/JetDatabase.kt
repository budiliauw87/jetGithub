package com.liau.jetgithub.core.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.liau.jetgithub.core.data.local.entity.User

/**
 * Created by Budiman on 26/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class JetDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: JetDatabase? = null

        fun getInstance(context: Context): JetDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    JetDatabase::class.java,
                    "JetGithubDatabase.db"
                ).build().apply {
                    INSTANCE = this
                }
            }

    }
}