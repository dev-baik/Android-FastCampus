package com.example.chapter03_5.repository

import com.example.chapter03_5.SearchService
import com.example.chapter03_5.model.ListItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchRepositoryImpl(private val searchService: SearchService) : SearchRepository {
    override fun search(query: String): Observable<List<ListItem>> {
        return searchService.searchImage(query)
            .zipWith(searchService.searchVideo(query)) { imageResult, videoResult ->
                mutableListOf<ListItem>().apply {
                    addAll(imageResult.documents)
                    addAll(videoResult.documents)
                }.sortedBy { it.dateTime }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}