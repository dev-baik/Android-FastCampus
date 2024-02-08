package com.example.chapter03_5

import com.example.chapter03_5.model.ImageListResponse
import com.example.chapter03_5.model.VideoListResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchService {

    @Headers("Authorization: KakaoAK b39f0cc0fe05839f5bb309eb3cad73fe")
    @GET("image")
    fun searchImage(@Query("query") query: String): Observable<ImageListResponse>

    @Headers("Authorization: KakaoAK b39f0cc0fe05839f5bb309eb3cad73fe")
    @GET("vclip")
    fun searchVideo(@Query("query") query: String): Observable<VideoListResponse>
}