package com.example.chapter02_4.network

import com.example.chapter02_4.model.Repo
import com.example.chapter02_4.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

//    @Headers("Authorization: Bearer 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")
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String, @Query("page") page: Int): Call<List<Repo>>

//    @Headers("Authorization: Bearer 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")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}