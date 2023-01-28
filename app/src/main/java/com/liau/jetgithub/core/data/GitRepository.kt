package com.liau.jetgithub.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.liau.jetgithub.core.data.local.AppPreferences
import com.liau.jetgithub.core.data.local.database.JetDatabase
import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.core.data.network.ApiService
import com.liau.jetgithub.core.data.network.pagingsource.FollowerPagingSource
import com.liau.jetgithub.core.data.network.pagingsource.GithubPagingSource
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.util.AppExecutors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalCoroutinesApi::class)
class GitRepository(
    private val apiService: ApiService,
    private val pref: AppPreferences,
    private val executors: AppExecutors,
    private val database: JetDatabase,
) {
    val querySearchFlow = MutableStateFlow("")
    val methodQuery = MutableStateFlow(1)
    val loginUser = MutableStateFlow("")
    val favorities = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = { database.userDao().getUserFavorite() }
    ).flow

    val items = querySearchFlow.flatMapLatest {
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                GithubPagingSource(it, 0, apiService)
            }
        ).flow
    }
    val detailItems = methodQuery.flatMapLatest {
        Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                FollowerPagingSource(loginUser.value, it, apiService)
            }
        ).flow
    }

    fun getPrefApp(): Flow<ConfigApp> {
        return pref.getPrefData()
    }

    fun countUser(loginId: String): Int {
        return database.userDao().countUser(loginId)
    }

    suspend fun saveLanguage(selectedLanguage: String) {
        return pref.saveLanguage(selectedLanguage)
    }

    suspend fun saveDarkTheme(isDark: Boolean) {
        return pref.saveDarkTheme(isDark)
    }

    fun deleteFavorite(user: User) {
        executors.diskIO().execute {
            database.userDao().deleteFavorite(user)
        }
    }

    fun setFavorite(user: User) {
        executors.diskIO().execute {
            database.userDao().insertFavorite(user)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: GitRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: AppPreferences,
            appExecutors: AppExecutors,
            jetDatabase: JetDatabase
        ): GitRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: GitRepository(apiService, pref, appExecutors, jetDatabase)
            }.also { INSTANCE = it }
    }

}