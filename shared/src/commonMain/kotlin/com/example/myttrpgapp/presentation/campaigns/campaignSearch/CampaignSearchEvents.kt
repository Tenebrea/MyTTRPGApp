package com.example.myttrpgapp.presentation.campaigns.campaignSearch

import com.example.myttrpgapp.domain.model.campaign.Campaign

interface CampaignSearchEvents {
    data class UpdateSearch(val value: String): CampaignSearchEvents
    data class ToggleFilter(val filter: CampaignFilter): CampaignSearchEvents
    object ClearFilters : CampaignSearchEvents
    object ToggleFiltersVisible : CampaignSearchEvents
    data class ApplyToCampaign(val campaign: Campaign): CampaignSearchEvents
    data class ToggleFavoriteCampaign(val campaign: Campaign): CampaignSearchEvents
}