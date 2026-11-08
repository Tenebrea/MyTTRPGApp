package com.example.myttrpgapp.presentation.campaigns.campaignSearch

import com.example.myttrpgapp.domain.model.campaign.Campaign

data class CampaignSearchUiState(
    val campaigns: List<Campaign> = emptyList(),
    val searchTerm: String = "",
    val filtersVisible: Boolean = false,
    val filterToggles: List<CampaignFilter> = emptyList(),
)