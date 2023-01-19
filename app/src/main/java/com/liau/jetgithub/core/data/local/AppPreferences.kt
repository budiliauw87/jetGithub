package com.liau.jetgithub.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class AppPreferences private constructor(private val dataStore: DataStore<Preferences>) {
    val darkMode = dataStore.data.map { it[DARKMODE_KEY] ?: "light" }
    val languageApp = dataStore.data.map { it[LANGUAGE_KEY] ?: "en" }

    companion object {
        private val LANGUAGE_KEY = stringPreferencesKey("language")
        private val DARKMODE_KEY = stringPreferencesKey("darkmode")

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
    suspend fun savePref(key: String, newValue: String) {
        val PREF_KEY = stringPreferencesKey(key)
        dataStore.edit {
            it[PREF_KEY] = newValue
        }
    }

}