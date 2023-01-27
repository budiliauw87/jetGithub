package com.liau.jetgithub.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.core.data.GitRepository
import com.liau.jetgithub.core.data.local.AppPreferences
import com.liau.jetgithub.core.data.local.database.JetDatabase
import com.liau.jetgithub.core.data.network.ApiConfig
import com.liau.jetgithub.util.AppExecutors

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings_jetgithub")

class ViewModelFactory(private val repo: GitRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injector.provideRepository(context))
            }.also { INSTANCE = it }
        }
    }
}


object Injector {
    fun provideRepository(context: Context): GitRepository {
        val database = JetDatabase.getInstance(context)
        val appExecutors = AppExecutors()
        val apiService = ApiConfig.provideApiService()
        val preferences = AppPreferences.getInstance(context.dataStore)
        return GitRepository.getInstance(apiService, preferences,appExecutors, database)
    }
}