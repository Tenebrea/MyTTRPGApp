package com.example.myttrpgapp.domain.model.tags

data class Tag(
    val id: Int,
    val name: String,
    val tagState: com.example.myttrpgapp.domain.model.tags.TagState,
    val tagCategory: com.example.myttrpgapp.domain.model.tags.TagCategory
)