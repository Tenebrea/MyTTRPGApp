package com.example.myttrpgapp.character.domain.model

data class Theme(
    val id: Int,
    val name: String,
    val description: String,
    val quest: String,
    val powerTags: List<Tag>,
    val weaknessTags: List<Tag>,
    val milestoneCount: Int,
    val improveCount: Int,
    val abandonCount: Int,
    val specialImprovements: List<SpecialImprovement>,
)
