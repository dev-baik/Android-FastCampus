package com.example.chapter03_6.model

class Empty : ListItem {
    override val viewType: ViewType
        get() = ViewType.EMPTY
}