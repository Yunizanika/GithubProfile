package id.polbeng.yun.girhubprofile.models

import com.google.gson.annotations.SerializedName

data class GithubUser(

	@field:SerializedName("twitter_username")
	val twitterUsername: Any,

	@field:SerializedName("bio")
	val bio: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("email")
	val email: String
)
