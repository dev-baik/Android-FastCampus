package com.example.chapter03_5.list

import com.example.chapter03_5.model.ListItem

interface ItemHandler {

    fun onClickFavorite(item: ListItem)
}