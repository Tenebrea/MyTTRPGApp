package com.example.myttrpgapp.character.domain.model.themes

import com.example.myttrpgapp.character.domain.model.tags.Tag

data class Theme(
    val id: Int,
    val name: Tag,
    val group: String,
    val might: Might,
    val description: String,
    val quest: String,
    val powerTags: List<Tag>,
    val weaknessTags: List<Tag>,
    val milestoneCount: Int,
    val improveCount: Int,
    val abandonCount: Int,
    val specialImprovements: List<SpecialImprovement>,
)