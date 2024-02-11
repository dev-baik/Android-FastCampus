package com.example.chapter03_8.domain.repository

import com.example.chapter03_8.domain.model.Content

interface ContentRepository {

    suspend fun save(item: Content): Boolean
}