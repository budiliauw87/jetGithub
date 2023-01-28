package com.liau.jetgithub.core.data.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liau.jetgithub.BuildConfig
import com.liau.jetgithub.core.data.network.ApiService
import com.liau.jetgithub.core.data.network.request.RequestGithub
import com.liau.jetgithub.core.data.network.response.EdgesItem
import com.liau.jetgithub.util.Util

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class GithubPagingSource(
    private val query: String,
    private val methodQuery : Int,
    private val apiService: ApiService,

    ) : PagingSource<String, EdgesItem>() {
    val token = BuildConfig.TOKEN
    override suspend fun load(params: LoadParams<String>): LoadResult<String, EdgesItem> {
        return try {
            val cursor = params.key ?: ""
            val queryGit = Util.getQueryGraph(query, cursor, methodQuery)
            val response = apiService.getUsers(token, RequestGithub(queryGit))
            val list = response.data?.search?.edges ?: listOf()
            val nextCursor: String? = list.last().cursor
            LoadResult.Page(
                data = list, prevKey = params.key, nextKey = nextCursor
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, EdgesItem>): String? {
        return null
    }


}