package com.liau.jetgithub.core.data.network

import com.liau.jetgithub.core.data.network.request.RequestGithub
import com.liau.jetgithub.core.data.network.response.Response
import com.liau.jetgithub.core.data.network.response.follower.FollowerResponse
import com.liau.jetgithub.core.data.network.response.following.FollowingResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
interface ApiService {
    @POST("graphql")
    suspend fun getUsers(
        @Header("Authorization") token: String,
        @Body params: RequestGithub
    ):Response

    @POST("graphql")
    suspend fun getFollowers(
        @Header("Authorization") token: String,
        @Body params: RequestGithub
    ):FollowerResponse

    @POST("graphql")
    suspend fun getFollowing(
        @Header("Authorization") token: String,
        @Body params: RequestGithub
    ): FollowingResponse

}