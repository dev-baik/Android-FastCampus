package com.example.chapter03_8.domain.usecase

import com.example.chapter03_8.domain.model.Content
import com.example.chapter03_8.domain.repository.ContentRepository
import javax.inject.Inject

class ContentUseCase @Inject constructor(
    private val contentRepository: ContentRepository
) {

    fun loadList() = contentRepository.loadList()

    suspend fun save(item: Content) = contentRepository.save(item)

    suspend fun delete(item: Content) = contentRepository.delete(item)
}