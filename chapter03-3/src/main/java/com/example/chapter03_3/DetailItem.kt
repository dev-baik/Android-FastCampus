package com.example.chapter03_3

import java.util.Date

data class DetailItem(
    val id: Long,
    val date: Date,
    val content: String,
    val amount: Long,
    val type: Type
)

enum class Type {
    PAY, CANCEL, POINT
}