package com.example.chapter02_6.chatlist

data class ChatRoomItem(
    val chatRoomId: String? = null,
    val otherUserName: String? = null,
    val lastMessage: String? = null,
    val otherUserId: String? = null
)