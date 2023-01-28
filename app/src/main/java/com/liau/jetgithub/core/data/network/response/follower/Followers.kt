package com.liau.jetgithub.core.data.network.response.follower

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.liau.jetgithub.core.data.network.response.EdgesItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class Followers(

	@field:SerializedName("edges")
	val edges: List<EdgesItem>,

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable