package com.example.myttrpgapp.character.domain.model

data class Tag(
    val id: Int,
    val name: String,
    val description: String?,
    val tagState: TagState,
    val tagCategory: TagCategory
)
