package com.example.myttrpgapp.character.domain.model

import androidx.compose.ui.graphics.ImageBitmap
import com.example.myttrpgapp.character.domain.model.statuses.Status
import com.example.myttrpgapp.character.domain.model.tags.Tag
import com.example.myttrpgapp.character.domain.model.themes.Theme

data class Character(
    val id: Int,
    val portrait: ImageBitmap?,
    val name: String,
    val description: String,
    val status: List<Status>,
    val themes: List<Theme>,
    val storyTags: List<Tag>,
    val backpackTags: List<Tag>,
    val fellowshipTheme: Theme,
    val relationships: Map<Character, Tag>,
    val promise: Int,
    val quintessence: List<Quintessence>
)
