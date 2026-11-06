package com.example.myttrpgapp.domain.model

import androidx.compose.ui.graphics.ImageBitmap
import com.example.myttrpgapp.domain.model.statuses.Status
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.themes.Theme

data class Character(
    val id: Int,
    val portrait: ImageBitmap?,
    val name: String,
    val description: String,
    val status: List<com.example.myttrpgapp.domain.model.statuses.Status>,
    val themes: List<com.example.myttrpgapp.domain.model.themes.Theme>,
    val storyTags: List<com.example.myttrpgapp.domain.model.tags.Tag>,
    val backpackTags: List<com.example.myttrpgapp.domain.model.tags.Tag>,
    val fellowshipTheme: com.example.myttrpgapp.domain.model.themes.Theme,
    val relationships: Map<com.example.myttrpgapp.domain.model.Character, com.example.myttrpgapp.domain.model.tags.Tag>,
    val promise: Int,
    val quintessence: List<com.example.myttrpgapp.domain.model.Quintessence>
)
