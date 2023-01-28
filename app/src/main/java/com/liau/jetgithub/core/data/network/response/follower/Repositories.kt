package com.liau.jetgithub.core.data.network.response.follower

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repositories(

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable