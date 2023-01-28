package com.liau.jetgithub.core.data.network.response.following

import UserFollowing
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


/**
 * Created by Budiliauw87 on 2023-01-28.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Parcelize
data class DataFollowing(
    @field:SerializedName("user")
    val user: UserFollowing? = null
) : Parcelable
