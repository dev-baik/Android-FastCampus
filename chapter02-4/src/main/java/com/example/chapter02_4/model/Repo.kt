package com.example.chapter02_4.model

import com.google.gson.annotations.SerializedName

data class Repo(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("stargazers_count")
    val startCount: String,
    @SerializedName("forks_count")
    val forkCount: String,
    @SerializedName("html_url")
    val htmlUrl: String,
)