package com.liau.jetgithub.core.data.local

import com.liau.jetgithub.core.data.local.database.UserDao
import com.liau.jetgithub.core.data.local.entity.User

/**
 * Created by Budiman on 27/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class LocalDataSource private constructor(private val userDao: UserDao) {
    companion object {
        @Volatile
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(userDao: UserDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(userDao)
            }.also { INSTANCE = it }
    }

    fun setFavorite(user: User) {
        userDao.insertFavorite(user)
    }

    fun deleteFavorite(user: User) {
        userDao.deleteFavorite(user)
    }

}