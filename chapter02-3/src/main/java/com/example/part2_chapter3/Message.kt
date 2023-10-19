package com.example.part2_chapter3

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message") val message: String
)
