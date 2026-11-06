package com.example.myttrpgapp.presentation.characterSheet

import com.example.myttrpgapp.domain.model.Quintessence
import com.example.myttrpgapp.domain.model.statuses.Status
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.themes.Theme

interface CharacterSheetEvent {
    data class OnPowerTagClick(val tag: com.example.myttrpgapp.domain.model.tags.Tag, val theme: com.example.myttrpgapp.domain.model.themes.Theme) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnWeaknessTagClick(val tag: com.example.myttrpgapp.domain.model.tags.Tag, val theme: com.example.myttrpgapp.domain.model.themes.Theme) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnPromiseClick(val promise: Int) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnMilestoneClick(val milestone: Int, val theme: com.example.myttrpgapp.domain.model.themes.Theme) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnAbandonClick(val abandon: Int, val theme: com.example.myttrpgapp.domain.model.themes.Theme) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnImproveClick(val improve: Int, val theme: com.example.myttrpgapp.domain.model.themes.Theme) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnBackpackTagClick(val tag: com.example.myttrpgapp.domain.model.tags.Tag) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStatusClick(val status: com.example.myttrpgapp.domain.model.statuses.Status) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStatusLevelClick(val status: com.example.myttrpgapp.domain.model.statuses.Status, val level: Int) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    object OnAddBackpackTagClick :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnBackpackTagChange(val tag: com.example.myttrpgapp.domain.model.tags.Tag, val value: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnBackpackTagRemove(val tag: com.example.myttrpgapp.domain.model.tags.Tag) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    object OnCharacterPortraitChange :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnCharacterNameChange(val name: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnCharacterDescriptionChange(val description: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStoryTagChanged(val tag: com.example.myttrpgapp.domain.model.tags.Tag, val value: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStoryTagRemove(val tag: com.example.myttrpgapp.domain.model.tags.Tag) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStatusChanged(val status: com.example.myttrpgapp.domain.model.statuses.Status, val value: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnStatusRemove(val status: com.example.myttrpgapp.domain.model.statuses.Status) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    object OnAddStoryTagClick :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    object OnAddStatusClick : com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnRemoveQuintessenceClick(val quintessence: com.example.myttrpgapp.domain.model.Quintessence) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnQuintessenceNameChange(val quintessence: com.example.myttrpgapp.domain.model.Quintessence, val value: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    data class OnQuintessenceDescriptionChange(val quintessence: com.example.myttrpgapp.domain.model.Quintessence, val value: String) :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
    object OnAddQuintessenceClick :
        com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent
}