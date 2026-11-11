package com.example.myttrpgapp.presentation.campaigns.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLocale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.domain.model.PlayerCharacter
import com.example.myttrpgapp.domain.model.User
import com.example.myttrpgapp.domain.model.campaign.Campaign
import com.example.myttrpgapp.domain.model.campaign.CampaignStatus
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.tags.TagCategory
import com.example.myttrpgapp.domain.model.tags.TagState
import com.example.myttrpgapp.domain.model.themes.Might
import com.example.myttrpgapp.domain.model.themes.Theme
import com.example.myttrpgapp.presentation.util.toImageBitmap
import kotlinx.datetime.LocalDate
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.campaigns
import myttrpgapp.shared.generated.resources.placeholder_image
import myttrpgapp.shared.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun CampaignComposable(
    campaign: Campaign,
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onApply: () -> Unit,
    onFavorite: () -> Unit,
    onClick: () -> Unit
) {
    CampaignComposable(
        modifier = modifier,
        onClick = onClick,
        onApply = onApply,
        onFavorite = onFavorite,
        name = campaign.name,
        description = campaign.description,
        characters = campaign.characters,
        players = campaign.players,
        gm = campaign.gm,
        maxPlayerCount = campaign.maxPlayerCount,
        price = campaign.price,
        status = campaign.status,
        isFavorite = isFavorite,
        startDate = campaign.startDate
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CampaignComposable(
    modifier: Modifier = Modifier,
    onApply: () -> Unit,
    onClick: () -> Unit,
    onFavorite: () -> Unit,
    name: String,
    description: String,
    characters: List<PlayerCharacter> = emptyList(),
    players: List<User> = emptyList(),
    gm: User,
    maxPlayerCount: Int = 4,
    startDate: LocalDate?,
    price: Int = 0,
    status: CampaignStatus = CampaignStatus.LOOKING_FOR_PLAYERS,
    isFavorite: Boolean = false
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header Row: Title & Favorite Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.displaySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable { onClick() }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    CampaignStatusChip(status = status)
                }

                IconButton(onClick = onFavorite) {
                    Text(
                        text = if (isFavorite) "★" else "☆",
                        style = MaterialTheme.typography.headlineMedium,
                        color = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                }
            }

            // Metadata Row: GM, Players Count, Price
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Game Master
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.profile),
                        contentDescription = "GM",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "GM: ${gm.name}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Player Slots
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Res.drawable.campaigns),
                        contentDescription = "Players",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${players.size} / $maxPlayerCount Players",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                // Price
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = if (price <= 0) "Free" else "$$price / session",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
                // Starting date
                if (startDate != null) {
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Text(
                            text = "${startDate.month.name.first() + startDate.month.name.substring(
                                1,
                                3
                            ).lowercase(LocalLocale.current.platformLocale)
                            } ${startDate.day}",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }

            }

            // Description Card
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            // Characters / Party Section
            if (characters.isNotEmpty()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Party (${characters.size})",
                        style = MaterialTheme.typography.titleMedium
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        characters.forEach { character ->
                            CharacterChip(character = character)
                        }
                    }
                }
            }

            // Action Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val buttonText = when (status) {
                    CampaignStatus.LOOKING_FOR_PLAYERS -> "Apply to Join"
                    CampaignStatus.STARTED -> "In Progress"
                    CampaignStatus.FINISHED -> "Finished"
                    CampaignStatus.PAUSED -> "Paused"
                    CampaignStatus.CANCELLED -> "Cancelled"
                }

                Button(
                    onClick = onApply,
                    enabled = status == CampaignStatus.LOOKING_FOR_PLAYERS,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = buttonText,
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
        }
    }
}

@Composable
fun CampaignStatusChip(
    status: CampaignStatus,
    modifier: Modifier = Modifier
) {
    val (statusText, containerColor, contentColor) = when (status) {
        CampaignStatus.LOOKING_FOR_PLAYERS -> Triple(
            "Looking for Players",
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.onPrimaryContainer
        )
        CampaignStatus.STARTED -> Triple(
            "Started",
            MaterialTheme.colorScheme.secondaryContainer,
            MaterialTheme.colorScheme.onSecondaryContainer
        )
        CampaignStatus.PAUSED -> Triple(
            "Paused",
            MaterialTheme.colorScheme.tertiaryContainer,
            MaterialTheme.colorScheme.onTertiaryContainer
        )
        CampaignStatus.FINISHED -> Triple(
            "Finished",
            MaterialTheme.colorScheme.surfaceVariant,
            MaterialTheme.colorScheme.onSurfaceVariant
        )
        CampaignStatus.CANCELLED -> Triple(
            "Cancelled",
            MaterialTheme.colorScheme.errorContainer,
            MaterialTheme.colorScheme.onErrorContainer
        )
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        color = containerColor
    ) {
        Text(
            text = statusText,
            style = MaterialTheme.typography.labelSmall,
            color = contentColor,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}

@Composable
fun CharacterChip(
    character: PlayerCharacter,
    modifier: Modifier = Modifier
) {
    val portraitBitmap: ImageBitmap = if (character.portrait == null) {
        val placeholderImage = painterResource(Res.drawable.placeholder_image)
        placeholderImage.toImageBitmap(
            size = placeholderImage.intrinsicSize,
            density = LocalDensity.current
        )
    } else {
        character.portrait
    }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        border = CardDefaults.outlinedCardBorder()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Image(
                bitmap = portraitBitmap,
                contentDescription = "Portrait of ${character.name}",
                modifier = Modifier
                    .size(20.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = character.name,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
fun CampaignComposablePreview() {
    TTRPGAppTheme(darkTheme = false) {
        val dummyGm = User(
            id = 1,
            name = "Dungeon Master Alex",
            email = "alex@example.com",
            password = "",
            description = "",
            campaigns = emptyList(),
            characters = emptyList()
        )
        val dummyPlayer = User(
            id = 2,
            name = "Player One",
            email = "player@example.com",
            password = "",
            description = "",
            campaigns = emptyList(),
            characters = emptyList()
        )
        val dummyCharacter = PlayerCharacter(
            id = 101,
            portrait = null,
            name = "Valeros",
            description = "Fighter",
            status = emptyList(),
            themes = emptyList(),
            storyTags = emptyList(),
            backpackTags = emptyList(),
            fellowshipTheme = Theme(
                id = 0,
                name = Tag(0, "Fellowship", TagState.UNSELECTED, TagCategory.POWER),
                description = "",
                quest = "",
                powerTags = emptyList(),
                weaknessTags = emptyList(),
                milestoneCount = 0,
                improveCount = 0,
                abandonCount = 0,
                specialImprovements = emptyList(),
                group = "",
                might = Might.ORIGIN
            ),
            relationships = emptyMap(),
            promise = 0,
            quintessence = emptyList()
        )

        CampaignComposable(
            name = "Whispers of the Ancient Forest",
            description = "A dark fantasy adventure set in an uncharted wilderness where mysterious fog corrupts ancient spirits. Party looking for brave adventurers to unravel the ancient mystery.",
            characters = listOf(dummyCharacter),
            players = listOf(dummyPlayer),
            gm = dummyGm,
            maxPlayerCount = 5,
            price = 15,
            status = CampaignStatus.LOOKING_FOR_PLAYERS,
            isFavorite = true,
            onApply = {},
            onFavorite = {},
            onClick = {},
            startDate = LocalDate(2026, 11, 20)
        )
    }
}
