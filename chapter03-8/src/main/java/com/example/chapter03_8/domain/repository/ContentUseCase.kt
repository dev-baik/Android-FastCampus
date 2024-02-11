package com.example.chapter03_8.domain.repository

import com.example.chapter03_8.domain.model.Content
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {
    suspend fun save(item: Content) = contentRepository.save(item)
}