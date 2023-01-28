package com.liau.jetgithub.core.data.network.response.follower

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
	@field:SerializedName("user")
	val user: User? = null
) : Parcelable