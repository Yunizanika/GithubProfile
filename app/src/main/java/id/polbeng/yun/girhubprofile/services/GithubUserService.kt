package id.polbeng.yun.girhubprofile.services

import id.polbeng.yun.girhubprofile.models.GithubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubUserService {
    @GET("users/{login}")
    fun loginUser(
        @Path("login") userLogin: String
    ): Call<GithubUser>
}