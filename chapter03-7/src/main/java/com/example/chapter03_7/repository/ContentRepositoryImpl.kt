package com.example.chapter03_7.repository

import com.example.chapter03_7.data.dao.ContentDao
import com.example.chapter03_7.model.ContentEntity
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(private val contentDao: ContentDao) :
    ContentRepository {

    override suspend fun insert(item: ContentEntity) {
        contentDao.insert(item)
    }
}