package com.liau.jetgithub.core.data

import com.liau.jetgithub.core.data.local.AppPreferences
import com.liau.jetgithub.core.data.network.ApiService
import kotlinx.coroutines.flow.Flow
import com.liau.jetgithub.core.model.ConfigApp

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class GitRepository(
    private val apiService: ApiService,
    private val pref: AppPreferences
) {

    fun getPrefApp(): Flow<ConfigApp> {
        return pref.getPrefData()
    }

    companion object {
        @Volatile
        private var INSTANCE: GitRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: AppPreferences
        ): GitRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GitRepository(apiService, pref)
            }.also { INSTANCE = it }
    }
}