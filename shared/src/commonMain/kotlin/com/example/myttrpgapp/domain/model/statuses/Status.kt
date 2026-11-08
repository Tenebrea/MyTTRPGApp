package com.example.myttrpgapp.domain.model.statuses

import com.example.myttrpgapp.domain.model.tags.TagState

data class Status(
    val id: Int,
    val name: String,
    val tagState: TagState = TagState.UNSELECTED,
    val markedValues: List<Int>
)