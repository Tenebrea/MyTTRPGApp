package com.example.myttrpgapp.character.presentation.characterSheet

import com.example.myttrpgapp.character.domain.model.Quintessence
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
    object OnAddBackpackTagClick : CharacterSheetEvent
    data class OnBackpackTagChange(val tag: Tag, val value: String) : CharacterSheetEvent
    data class OnBackpackTagRemove(val tag: Tag) : CharacterSheetEvent
    object OnCharacterPortraitChange : CharacterSheetEvent
    data class OnCharacterNameChange(val name: String) : CharacterSheetEvent
    data class OnCharacterDescriptionChange(val description: String) : CharacterSheetEvent
    data class OnStoryTagChanged(val tag: Tag, val value: String) : CharacterSheetEvent
    data class OnStoryTagRemove(val tag: Tag) : CharacterSheetEvent
    data class OnStatusChanged(val status: Status, val value: String) : CharacterSheetEvent
    data class OnStatusRemove(val status: Status) : CharacterSheetEvent
    object OnAddStoryTagClick : CharacterSheetEvent
    object OnAddStatusClick : CharacterSheetEvent
    data class OnRemoveQuintessenceClick(val quintessence: Quintessence) : CharacterSheetEvent
    data class OnQuintessenceNameChange(val quintessence: Quintessence, val value: String) : CharacterSheetEvent
    data class OnQuintessenceDescriptionChange(val quintessence: Quintessence, val value: String) : CharacterSheetEvent
    object OnAddQuintessenceClick : CharacterSheetEvent
}