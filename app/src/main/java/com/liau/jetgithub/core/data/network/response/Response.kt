package com.liau.jetgithub.core.data.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Response(
	@field:SerializedName("data")
	val data: Data? = null
) : Parcelable

@Parcelize
data class Node(
	@field:SerializedName("followers")
	val followers: Followers? = null,

	@field:SerializedName("avatarUrl")
	val avatarUrl: String? = null,

	@field:SerializedName("repositories")
	val repositories: Repositories? = null,

	@field:SerializedName("following")
	val following: Following? = null,

	@field:SerializedName("name")
	val name: @RawValue Any? = null,

	@field:SerializedName("location")
	val location: @RawValue Any? = null,

	@field:SerializedName("company")
	val company: @RawValue Any? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("email")
	val email: String? = null
) : Parcelable

@Parcelize
data class Repositories(

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable

@Parcelize
data class EdgesItem(

	@field:SerializedName("cursor")
	val cursor: String? = null,

	@field:SerializedName("node")
	val node: Node? = null
) : Parcelable

@Parcelize
data class Following(

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable

@Parcelize
data class Search(
	@field:SerializedName("userCount")
	val userCount: Int? = null,

	@field:SerializedName("edges")
	val edges: List<EdgesItem>
) : Parcelable

@Parcelize
data class Data(

	@field:SerializedName("search")
	val search: Search? = null
) : Parcelable

@Parcelize
data class Followers(

	@field:SerializedName("totalCount")
	val totalCount: Int? = null
) : Parcelable
