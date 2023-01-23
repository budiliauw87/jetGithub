package com.liau.jetgithub.core.data

import com.liau.jetgithub.core.data.local.AppPreferences
import com.liau.jetgithub.core.data.network.ApiService
import com.liau.jetgithub.core.model.ConfigApp
import kotlinx.coroutines.flow.Flow

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

    suspend fun saveLanguage(selectedLanguage :String) {
        return pref.saveLanguage(selectedLanguage)
    }
    suspend fun saveDarkTheme(isDark :Boolean) {
        return pref.saveDarkTheme(isDark)
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