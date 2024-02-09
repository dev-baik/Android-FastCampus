package com.example.chapter03_6.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.chapter03_6.model.ListItem
import com.example.chapter03_6.remote.MainPagingSource
import com.example.chapter03_6.remote.MainService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
) : MainRepository {

    override fun loadList(): Flow<PagingData<ListItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MainPagingSource(mainService)
            }
        ).flow
    }
}