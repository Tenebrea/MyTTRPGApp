package com.example.myttrpgapp.domain.model.statuses

import com.example.myttrpgapp.domain.model.tags.TagState

data class Status(
    val id: Int,
    val name: String,
    val tagState: com.example.myttrpgapp.domain.model.tags.TagState = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
    val markedValues: List<Int>
)