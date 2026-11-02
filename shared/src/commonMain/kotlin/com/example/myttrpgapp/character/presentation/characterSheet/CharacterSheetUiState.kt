package com.example.myttrpgapp.character.presentation.characterSheet

import com.example.myttrpgapp.character.domain.model.Character
import com.example.myttrpgapp.character.domain.model.tags.Tag
import com.example.myttrpgapp.character.domain.model.themes.Might
import com.example.myttrpgapp.character.domain.model.themes.Theme

data class CharacterSheetUiState(
    val character: Character = Character(
        id = 0,
        name = "",
        description = "",
        status = emptyList(),
        themes = emptyList(),
        storyTags = emptyList(),
        backpackTags = emptyList(),
        fellowshipTheme = Theme(
            id = 0,
            name = Tag(
                id = 0,
                name = "",
                tagState = com.example.myttrpgapp.character.domain.model.tags.TagState.UNSELECTED,
                tagCategory = com.example.myttrpgapp.character.domain.model.tags.TagCategory.POWER
            ),
            group = "",
            might = Might.ORIGIN,
            description = "",
            quest = "",
            powerTags = emptyList(),
            weaknessTags = emptyList(),
            milestoneCount = 0,
            improveCount = 0,
            abandonCount = 0,
            specialImprovements = emptyList(),
        ),
        relationships = emptyMap(),
        promise = 0,
        quintessence = emptyList(),
        portrait = null
    )
)
