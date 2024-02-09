package com.example.chapter03_6.model

data class Image(
    val imageUrl: String
) : ListItem {
    override val viewType: ViewType
        get() = ViewType.IMAGE

}
