package com.example.myttrpgapp.character.presentation.characterSheet

import com.example.myttrpgapp.character.domain.model.statuses.Status
import com.example.myttrpgapp.character.domain.model.tags.Tag
import com.example.myttrpgapp.character.domain.model.themes.Theme

interface CharacterSheetEvent {
    data class OnPowerTagClick(val tag: Tag, val theme: Theme) : CharacterSheetEvent
    data class OnWeaknessTagClick(val tag: Tag, val theme: Theme) : CharacterSheetEvent
    data class OnPromiseClick(val promise: Int) : CharacterSheetEvent
    data class OnMilestoneClick(val milestone: Int, val theme: Theme) : CharacterSheetEvent
    data class OnAbandonClick(val abandon: Int, val theme: Theme) : CharacterSheetEvent
    data class OnImproveClick(val improve: Int, val theme: Theme) : CharacterSheetEvent
    data class OnBackpackTagClick(val tag: Tag) : CharacterSheetEvent
    data class OnStatusClick(val status: Status) : CharacterSheetEvent
    data class OnStatusLevelClick(val status: Status, val level: Int) : CharacterSheetEvent
}