package com.example.chapter03_6.remote.repository

import androidx.paging.PagingData
import com.example.chapter03_6.model.ListItem
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun loadList(): Flow<PagingData<ListItem>>
}