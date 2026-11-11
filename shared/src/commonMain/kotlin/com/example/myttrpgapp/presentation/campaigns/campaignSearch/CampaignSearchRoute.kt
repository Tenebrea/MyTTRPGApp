package com.example.myttrpgapp.presentation.campaigns.campaignSearch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.domain.model.User
import com.example.myttrpgapp.domain.model.campaign.Campaign
import com.example.myttrpgapp.domain.model.campaign.CampaignStatus
import com.example.myttrpgapp.presentation.campaigns.common.CampaignComposable
import kotlinx.datetime.LocalDate
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.campaigns
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CampaignSearchRoute(
    modifier: Modifier = Modifier,
    uiState: CampaignSearchUiState = CampaignSearchUiState(),
    onEvent: (CampaignSearchEvents) -> Unit,
    onCampaignClick: (Campaign) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 320.dp),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header Section
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Explore Campaigns",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Find tabletop RPG games to join or browse active adventures",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Search Bar & Filter Controls Section
            item(span = { GridItemSpan(maxLineSpan) }) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = uiState.searchTerm,
                            onValueChange = { onEvent(CampaignSearchEvents.UpdateSearch(it)) },
                            modifier = Modifier.weight(1f),
                            placeholder = {
                                Text(
                                    text = "Search campaigns, GMs...",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(Res.drawable.campaigns),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(20.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            trailingIcon = {
                                if (uiState.searchTerm.isNotEmpty()) {
                                    IconButton(onClick = { onEvent(CampaignSearchEvents.UpdateSearch("")) }) {
                                        Text(
                                            text = "✕",
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )

                        // Filter Toggle Button
                        Surface(
                            onClick = { onEvent(CampaignSearchEvents.ToggleFiltersVisible) },
                            shape = RoundedCornerShape(12.dp),
                            color = if (uiState.filtersVisible || uiState.filterToggles.isNotEmpty()) {
                                MaterialTheme.colorScheme.primaryContainer
                            } else {
                                MaterialTheme.colorScheme.surfaceVariant
                            },
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 14.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = "⚙ Filters",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (uiState.filtersVisible || uiState.filterToggles.isNotEmpty()) {
                                        MaterialTheme.colorScheme.onPrimaryContainer
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant
                                    }
                                )
                                if (uiState.filterToggles.isNotEmpty()) {
                                    Surface(
                                        shape = RoundedCornerShape(10.dp),
                                        color = MaterialTheme.colorScheme.primary
                                    ) {
                                        Text(
                                            text = uiState.filterToggles.size.toString(),
                                            style = MaterialTheme.typography.labelSmall,
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Collapsible Filter Chips Row
                    AnimatedVisibility(
                        visible = uiState.filtersVisible,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            border = CardDefaults.outlinedCardBorder()
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "Filter by",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    CampaignFilter.entries.forEach { filter ->
                                        val isSelected = uiState.filterToggles.contains(filter)
                                        val filterLabel = when (filter) {
                                            CampaignFilter.FREE -> "Free Sessions"
                                            CampaignFilter.LOOKING_FOR_PLAYERS -> "Looking for Players"
                                        }

                                        FilterChip(
                                            selected = isSelected,
                                            onClick = { onEvent(CampaignSearchEvents.ToggleFilter(filter)) },
                                            label = { Text(text = filterLabel) },
                                            leadingIcon = if (isSelected) {
                                                { Text("✓", style = MaterialTheme.typography.labelMedium) }
                                            } else null,
                                            colors = FilterChipDefaults.filterChipColors(
                                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Results Count & Clear Header
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${uiState.campaigns.size} ${if (uiState.campaigns.size == 1) "Campaign" else "Campaigns"} Available",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    if (uiState.searchTerm.isNotEmpty() || uiState.filterToggles.isNotEmpty()) {
                        OutlinedButton(
                            onClick = {
                                onEvent(CampaignSearchEvents.UpdateSearch(""))
                                onEvent(CampaignSearchEvents.ClearFilters)
                            },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "Clear All",
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }

            // Empty State or Adaptive Grid List of Campaigns
            if (uiState.campaigns.isEmpty()) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 32.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                        ),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "⚔️",
                                style = MaterialTheme.typography.displayMedium
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No Campaigns Found",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Try adjusting your search keywords or active filters to find available games.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(
                                onClick = {
                                    onEvent(CampaignSearchEvents.UpdateSearch(""))
                                    onEvent(CampaignSearchEvents.ClearFilters)
                                },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(text = "Reset Search & Filters")
                            }
                        }
                    }
                }
            } else {
                items(
                    items = uiState.campaigns,
                    key = { it.id }
                ) { campaign ->
                    CampaignComposable(
                        campaign = campaign,
                        isFavorite = campaign.isFavorite,
                        onClick = { onCampaignClick(campaign) },
                        onApply = { onEvent(CampaignSearchEvents.ApplyToCampaign(campaign)) },
                        onFavorite = { onEvent(CampaignSearchEvents.ToggleFavoriteCampaign(campaign)) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CampaignSearchRoutePreview() {
    val dummyGm = User(
        id = 1,
        name = "GM Dave",
        email = "dave@example.com",
        password = "",
        description = "",
        campaigns = emptyList(),
        characters = emptyList()
    )

    val sampleCampaigns = listOf(
        Campaign(
            id = 1,
            name = "Curse of Strahd - Gothic Horror",
            description = "Under raging storm clouds, the vampire Count Strahd von Zarovich stands silhouetted against the ancient walls of Castle Ravenloft.",
            characters = emptyList(),
            players = emptyList(),
            maxPlayerCount = 5,
            price = 0,
            status = CampaignStatus.LOOKING_FOR_PLAYERS,
            gm = dummyGm,
            startDate = LocalDate(2026, 11, 20),
            isFavorite = true
        ),
        Campaign(
            id = 2,
            name = "Lost Mine of Phandelver",
            description = "Explore mysterious caverns and battle goblins in this classic fantasy adventure.",
            characters = emptyList(),
            players = emptyList(),
            maxPlayerCount = 4,
            price = 15,
            status = CampaignStatus.STARTED,
            gm = dummyGm,
            startDate = LocalDate(2026, 11, 20),
            isFavorite = false
        )
    )

    TTRPGAppTheme {
        CampaignSearchRoute(
            uiState = CampaignSearchUiState(
                campaigns = sampleCampaigns,
                filtersVisible = true,
                filterToggles = listOf(CampaignFilter.LOOKING_FOR_PLAYERS)
            ),
            onEvent = {  },
            onCampaignClick = {  }
        )
    }
}
