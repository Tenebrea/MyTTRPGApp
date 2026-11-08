package com.example.myttrpgapp.presentation.characterSheet

data class CharacterSheetUiState(
    val character: com.example.myttrpgapp.domain.model.PlayerCharacter = com.example.myttrpgapp.domain.model.PlayerCharacter(
        id = 0,
        name = "",
        description = "",
        status = emptyList(),
        themes = emptyList(),
        storyTags = emptyList(),
        backpackTags = emptyList(),
        fellowshipTheme = com.example.myttrpgapp.domain.model.themes.Theme(
            id = 0,
            name = com.example.myttrpgapp.domain.model.tags.Tag(
                id = 0,
                name = "",
                tagState = com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                tagCategory = com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
            ),
            group = "",
            might = com.example.myttrpgapp.domain.model.themes.Might.ORIGIN,
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
