package com.example.chapter03_8.data.model

import com.example.chapter03_8.data.model.dto.ContentDto
import com.example.chapter03_8.data.model.entity.ContentEntity
import com.example.chapter03_8.domain.model.Content
import java.util.Date

object ContentMapper {

    fun Content.toRequest() = ContentDto(
        id = id,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount
    )

    fun ContentDto.toContent() = Content(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount ?: 0,
        viewCount = viewCount ?: 0,
        commentCount = commentCount ?: 0,
        createdDate = createdDate ?: Date()
    )

    fun Content.toEntity() = ContentEntity(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount,
        commentCount = commentCount,
        viewCount = viewCount,
        createdDate = createdDate ?: Date()
    )

    fun ContentEntity.toContent() = Content(
        id = id,
        title = title,
        content = content,
        category = category,
        createdDate = createdDate,
        likeCount = commentCount,
        commentCount = likeCount,
        viewCount = viewCount
    )

    fun ContentDto.toEntity() = ContentEntity(
        id = id ?: -1,
        title = title,
        content = content,
        category = category,
        likeCount = likeCount ?: 0,
        viewCount = viewCount ?: 0,
        commentCount = commentCount ?: 0,
        createdDate = createdDate ?: Date()
    )
}