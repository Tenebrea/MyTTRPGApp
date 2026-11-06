package com.example.myttrpgapp.presentation.characterSheet

import com.example.myttrpgapp.domain.model.Character
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.themes.Might
import com.example.myttrpgapp.domain.model.themes.Theme

data class CharacterSheetUiState(
    val character: com.example.myttrpgapp.domain.model.Character = _root_ide_package_.com.example.myttrpgapp.domain.model.Character(
        id = 0,
        name = "",
        description = "",
        status = emptyList(),
        themes = emptyList(),
        storyTags = emptyList(),
        backpackTags = emptyList(),
        fellowshipTheme = _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Theme(
            id = 0,
            name = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                id = 0,
                name = "",
                tagState = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                tagCategory = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
            ),
            group = "",
            might = _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Might.ORIGIN,
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
