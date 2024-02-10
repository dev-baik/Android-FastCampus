package com.example.chapter03_7.repository

import com.example.chapter03_7.model.ContentEntity

interface ContentRepository {

    suspend fun insert(item: ContentEntity)
}