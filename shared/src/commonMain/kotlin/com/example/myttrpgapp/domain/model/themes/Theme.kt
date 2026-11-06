package com.example.myttrpgapp.domain.model.themes

import com.example.myttrpgapp.domain.model.tags.Tag

data class Theme(
    val id: Int,
    val name: com.example.myttrpgapp.domain.model.tags.Tag,
    val group: String,
    val might: com.example.myttrpgapp.domain.model.themes.Might,
    val description: String,
    val quest: String,
    val powerTags: List<com.example.myttrpgapp.domain.model.tags.Tag>,
    val weaknessTags: List<com.example.myttrpgapp.domain.model.tags.Tag>,
    val milestoneCount: Int,
    val improveCount: Int,
    val abandonCount: Int,
    val specialImprovements: List<com.example.myttrpgapp.domain.model.themes.SpecialImprovement>,
)