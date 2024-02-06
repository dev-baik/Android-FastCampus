package com.example.chapter03_4.mvi.repository

import com.example.chapter03_4.mvi.model.Image

interface ImageRepository {

    suspend fun getRandomImage(): Image
}