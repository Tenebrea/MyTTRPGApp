package com.example.myttrpgapp.presentation.profile

import com.example.myttrpgapp.domain.model.PlayerCharacter
import com.example.myttrpgapp.domain.model.UserAvatars
import com.example.myttrpgapp.domain.model.campaign.Campaign

sealed interface ProfileEvent {
    object OnEditClick : ProfileEvent
    object OnSaveClick : ProfileEvent
    object OnCancelEditClick : ProfileEvent
    data class OnNameChange(val name: String) : ProfileEvent
    data class OnEmailChange(val email: String) : ProfileEvent
    data class OnDescriptionChange(val description: String) : ProfileEvent
    data class OnAvatarSelect(val avatar: UserAvatars) : ProfileEvent
    data class OnCampaignClick(val campaign: Campaign) : ProfileEvent
    data class OnCharacterClick(val character: PlayerCharacter) : ProfileEvent
    object OnChangePasswordClick : ProfileEvent
    object OnLogoutClick : ProfileEvent
}
