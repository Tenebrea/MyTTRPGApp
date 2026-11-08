package com.example.myttrpgapp.presentation.campaigns.campaignSearch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myttrpgapp.domain.model.campaign.Campaign

@Composable
fun CampaignSearchListRoute(
    modifier: Modifier = Modifier,
    uiState: CampaignSearchUiState = CampaignSearchUiState(),
    onEvent: (CampaignSearchEvents) -> Unit,
    onCampaignClick: (Campaign) -> Unit = {},
) {
    CampaignSearchRoute(
        modifier = modifier,
        uiState = uiState,
        onEvent = onEvent,
        onCampaignClick = onCampaignClick
    )
}
