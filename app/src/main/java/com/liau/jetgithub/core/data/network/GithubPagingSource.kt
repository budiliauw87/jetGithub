package com.liau.jetgithub.core.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liau.jetgithub.BuildConfig
import com.liau.jetgithub.core.data.network.request.RequestGithub
import com.liau.jetgithub.core.data.network.response.EdgesItem

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class GithubPagingSource(
    private val apiService: ApiService,
) : PagingSource<String, EdgesItem>() {
    val token = BuildConfig.TOKEN
    override suspend fun load(params: LoadParams<String>): LoadResult<String, EdgesItem> {
        return try {
            val cursor = params.key ?: ""
            val queryGit = getQueryGraph("", cursor, 0)
            val response = apiService.getUsers(token, RequestGithub(queryGit))
            val list = response.data?.search?.edges ?: listOf<EdgesItem>()
            val nextCursor: String? = list.last().cursor
            LoadResult.Page(
                data = list,
                prevKey = params.key,
                nextKey = nextCursor
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, EdgesItem>): String? {
        return null
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