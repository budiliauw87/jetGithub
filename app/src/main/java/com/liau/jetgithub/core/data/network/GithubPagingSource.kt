package com.liau.jetgithub.core.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liau.jetgithub.core.data.network.response.Response

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
class GithubPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Response>() {
    override fun getRefreshKey(state: PagingState<Int, Response>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2).coerceAtLeast(0)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Response> {
        return try {
            val position = params.key ?: 1
            val token = "Bearer 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"
            LoadResult.Error(Exception("Error"))
            /*
            val response = apiService.getUsers(token)
            LoadResult.Page(
                data = response.listStory,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (response.listStory.isEmpty()) null else position + 1
            )*/
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}