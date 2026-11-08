package com.example.myttrpgapp.domain.model.campaign

import com.example.myttrpgapp.domain.model.PlayerCharacter
import com.example.myttrpgapp.domain.model.User
import kotlinx.datetime.LocalDate

data class Campaign(
    val id: Int,
    val name: String,
    val description: String,
    val characters: List<PlayerCharacter>,
    val startDate: LocalDate?,
    val players: List<User>,
    val maxPlayerCount: Int,
    val price: Int,
    val status: CampaignStatus,
    val gm: User,
    val isFavorite: Boolean
)