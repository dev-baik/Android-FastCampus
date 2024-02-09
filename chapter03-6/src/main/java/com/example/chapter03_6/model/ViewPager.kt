package com.example.chapter03_6.model

data class ViewPager(
    val items : List<ListItem>
): ListItem {
    override val viewType: ViewType
        get() = ViewType.VIEW_PAGER
}