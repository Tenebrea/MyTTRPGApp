package com.example.myttrpgapp.domain.model

import com.example.myttrpgapp.domain.model.campaign.Campaign

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val campaigns: List<Campaign>,
    val characters: List<PlayerCharacter>
)
