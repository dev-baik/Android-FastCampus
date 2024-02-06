package com.example.chapter03_4

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageService {

    @Headers("Authorization: Client-ID yR1EUSR5bqP1CJGPkWNlnxPTjaTJ63eHoWgpfClNfrA")
    @GET("photos/random")
    fun getRandomImage(): Call<ImageResponse>
}