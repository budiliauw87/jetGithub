package com.liau.jetgithub.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.liau.jetgithub.BuildConfig
import com.liau.jetgithub.core.data.local.AppPreferences
import com.liau.jetgithub.core.data.network.ApiService
import com.liau.jetgithub.core.data.network.GithubPagingSource
import com.liau.jetgithub.core.data.network.request.RequestGithub
import com.liau.jetgithub.core.data.network.response.EdgesItem
import com.liau.jetgithub.core.data.network.response.Response
import com.liau.jetgithub.core.model.ConfigApp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class GitRepository(
    private val apiService: ApiService,
    private val pref: AppPreferences
) {
    val token = BuildConfig.TOKEN
    fun getPrefApp(): Flow<ConfigApp> {
        return pref.getPrefData()
    }

    fun getUserPaging(): Flow<PagingData<EdgesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                GithubPagingSource(apiService)
            }
        ).flow
    }

    suspend fun saveLanguage(selectedLanguage: String) {
        return pref.saveLanguage(selectedLanguage)
    }

    suspend fun saveDarkTheme(isDark: Boolean) {
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

    suspend fun getUser(): Flow<Response> {
        return flow {
            val queryGit = getQueryGraph("", "", 0)
            val response = apiService.getUsers(token, RequestGithub(queryGit))
            emit(response)
        }
    }

    fun getQueryGraph(query: String, lastCursor: String, method: Int): String {
        val resultQueryGraph: String
        val querySearch: String
        val cursorAfter: String
        when (method) {
            0 -> {
                querySearch = if (query.isEmpty()) "language:java" else query
                cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                resultQueryGraph =
                    "query { search( query: \"" + querySearch + "\", type: USER, first:10" + cursorAfter +
                            ") {userCount edges { node { ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories { totalCount }}}cursor}}}"
            }
            1 -> {
                cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                resultQueryGraph =
                    "query { user( login: \"" + query + "\" ) { followers( first:10" + cursorAfter +
                            " ) { edges{ node{ ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories{ totalCount }}} cursor}}}}"
            }
            2 -> {
                cursorAfter = if (lastCursor.isEmpty()) "" else ",after:\"$lastCursor\""
                resultQueryGraph =
                    "query { user( login: \"" + query + "\" ) { following( first:10" + cursorAfter +
                            " ) { edges{ node{ ... on User { id login name location email company avatarUrl followers " + "{ totalCount } following { totalCount } repositories{ totalCount }}} cursor}}}}"
            }
            else -> resultQueryGraph = ""
        }
        return resultQueryGraph
    }
}