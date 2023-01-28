package com.liau.jetgithub.core.data.network.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.liau.jetgithub.BuildConfig
import com.liau.jetgithub.core.data.network.ApiService
import com.liau.jetgithub.core.data.network.request.RequestGithub
import com.liau.jetgithub.core.data.network.response.EdgesItem
import com.liau.jetgithub.util.Util


/**
 * Created by Budiliauw87 on 2023-01-28.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
class FollowerPagingSource(
    private val query: String,
    private val methodQuery: Int,
    private val apiService: ApiService,

    ) : PagingSource<String, EdgesItem>() {
    val token = BuildConfig.TOKEN
    override suspend fun load(params: LoadParams<String>): LoadResult<String, EdgesItem> {
        return try {
            val cursor = params.key ?: ""
            val queryGit = Util.getQueryGraph(query, cursor, methodQuery)
            if(methodQuery == 1){
                val response = apiService.getFollowers(token, RequestGithub(queryGit))
                val list = response.data?.user?.followers?.edges ?: listOf()
                val nextCursor: String? = list.last().cursor
                LoadResult.Page(data = list, prevKey = params.key, nextKey = nextCursor)
            }else{
                val response = apiService.getFollowing(token, RequestGithub(queryGit))
                val list = response.data?.user?.following?.edges ?: listOf()
                val nextCursor: String? = list.last().cursor
                LoadResult.Page(data = list, prevKey = params.key, nextKey = nextCursor)
            }
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, EdgesItem>): String? {
        return null
    }
}