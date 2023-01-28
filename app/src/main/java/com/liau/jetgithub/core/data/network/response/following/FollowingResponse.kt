package com.liau.jetgithub.core.data.network.response.following

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Budiliauw87 on 2023-01-28.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Parcelize
data class FollowingResponse(
    @field:SerializedName("data")
    val data: DataFollowing? = null
) : Parcelable