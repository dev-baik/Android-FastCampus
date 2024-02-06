package com.example.chapter03_4.mvp.repository

interface ImageRepository {

    fun getRandomImage(callBack: CallBack)

    interface CallBack {
        fun loadImage(url: String, color: String)
    }
}