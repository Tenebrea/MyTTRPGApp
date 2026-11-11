package com.example.myttrpgapp.presentation.profile

import com.example.myttrpgapp.domain.model.User
import com.example.myttrpgapp.domain.model.UserAvatars

data class ProfileUiState(
    val user: User = User(
        id = 0,
        name = "",
        email = "",
        password = "",
        description = "",
        campaigns = emptyList(),
        characters = emptyList()
    ),
    val selectedAvatar: UserAvatars = UserAvatars.AVATAR1,
    val isEditing: Boolean = false,
    val editName: String = "",
    val editEmail: String = "",
    val editDescription: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
