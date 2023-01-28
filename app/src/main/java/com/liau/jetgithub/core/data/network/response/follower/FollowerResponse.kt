package com.liau.jetgithub.core.data.network.response.follower

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FollowerResponse(
	@field:SerializedName("data")
	val data: Data? = null
) : Parcelable