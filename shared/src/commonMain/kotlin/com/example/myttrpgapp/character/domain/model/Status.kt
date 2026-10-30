package com.example.myttrpgapp.character.domain.model

data class Status(
    val id: Int,
    val name: String,
    val tagState: TagState = TagState.UNSELECTED,
    val markedValues: List<Int>
)
