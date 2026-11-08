package com.example.myttrpgapp.domain.model

import androidx.compose.ui.graphics.ImageBitmap
import com.example.myttrpgapp.domain.model.statuses.Status
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.themes.Theme

data class PlayerCharacter(
    val id: Int,
    val portrait: ImageBitmap?,
    val name: String,
    val description: String,
    val status: List<Status>,
    val themes: List<Theme>,
    val storyTags: List<Tag>,
    val backpackTags: List<Tag>,
    val fellowshipTheme: Theme,
    val relationships: Map<PlayerCharacter, Tag>,
    val promise: Int,
    val quintessence: List<Quintessence>
)
