package com.liau.jetgithub.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.liau.jetgithub.core.model.ConfigApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class AppPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private val DARKMODE_KEY = booleanPreferencesKey("darkmode")

        @Volatile
        private var INSTANCE: AppPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = AppPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }


    //save pref
    suspend fun saveLanguage(newValue: String) {
        dataStore.edit {
            it[LANGUAGE_KEY] = newValue
        }
    }


    suspend fun saveDarkTheme(newValue: Boolean) {
        dataStore.edit {
            it[DARKMODE_KEY] = newValue
        }
    }

    fun getPrefData(): Flow<ConfigApp> {
        return dataStore.data.map {
            val language = it[LANGUAGE_KEY] ?: "en"
            val darkMode = it[DARKMODE_KEY] ?: false
            ConfigApp(language, darkMode)
        }
    }

}