package com.example.myttrpgapp.character.domain.model.tags

data class Tag(
    val id: Int,
    val name: String,
    val tagState: TagState,
    val tagCategory: TagCategory
)