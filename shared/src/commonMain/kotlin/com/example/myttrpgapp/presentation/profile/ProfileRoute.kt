package com.example.myttrpgapp.presentation.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.domain.model.PlayerCharacter
import com.example.myttrpgapp.domain.model.User
import com.example.myttrpgapp.domain.model.UserAvatars
import com.example.myttrpgapp.domain.model.campaign.Campaign
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.campaigns
import myttrpgapp.shared.generated.resources.character_sheet
import myttrpgapp.shared.generated.resources.edit
import myttrpgapp.shared.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    uiState: ProfileUiState = ProfileUiState(),
    onEvent: (ProfileEvent) -> Unit = {},
    onEditClick: () -> Unit = { onEvent(ProfileEvent.OnEditClick) },
    onSaveClick: () -> Unit = { onEvent(ProfileEvent.OnSaveClick) },
    onCancelEditClick: () -> Unit = { onEvent(ProfileEvent.OnCancelEditClick) },
    onNameChange: (String) -> Unit = { onEvent(ProfileEvent.OnNameChange(it)) },
    onEmailChange: (String) -> Unit = { onEvent(ProfileEvent.OnEmailChange(it)) },
    onDescriptionChange: (String) -> Unit = { onEvent(ProfileEvent.OnDescriptionChange(it)) },
    onAvatarSelect: (UserAvatars) -> Unit = { onEvent(ProfileEvent.OnAvatarSelect(it)) },
    onCampaignClick: (Campaign) -> Unit = { onEvent(ProfileEvent.OnCampaignClick(it)) },
    onCharacterClick: (PlayerCharacter) -> Unit = { onEvent(ProfileEvent.OnCharacterClick(it)) },
    onChangePasswordClick: () -> Unit = { onEvent(ProfileEvent.OnChangePasswordClick) },
    onLogoutClick: () -> Unit = { onEvent(ProfileEvent.OnLogoutClick) }
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Error banner if any
                if (uiState.errorMessage != null) {
                    item {
                        Surface(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            color = MaterialTheme.colorScheme.errorContainer
                        ) {
                            Text(
                                text = uiState.errorMessage,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onErrorContainer,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }

                // Profile Header Card
                item {
                    ProfileHeaderCard(
                        uiState = uiState,
                        onEditClick = onEditClick,
                        onSaveClick = onSaveClick,
                        onCancelEditClick = onCancelEditClick,
                        onNameChange = onNameChange,
                        onEmailChange = onEmailChange,
                        onAvatarSelect = onAvatarSelect
                    )
                }

                // Quick Stats Card
                item {
                    ProfileStatsCard(
                        campaignsCount = uiState.user.campaigns.size,
                        charactersCount = uiState.user.characters.size
                    )
                }

                // Adventurer Bio Card
                item {
                    ProfileBioCard(
                        uiState = uiState,
                        onDescriptionChange = onDescriptionChange
                    )
                }

                // Active Campaigns Section
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.campaigns),
                            contentDescription = "Campaigns",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Active Campaigns (${uiState.user.campaigns.size})",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                if (uiState.user.campaigns.isEmpty()) {
                    item {
                        EmptySectionCard(message = "No active campaigns found. Join or create a campaign to begin your adventure!")
                    }
                } else {
                    items(
                        items = uiState.user.campaigns,
                        key = { it.id }
                    ) { campaign ->
                        ProfileCampaignCard(
                            campaign = campaign,
                            onClick = { onCampaignClick(campaign) }
                        )
                    }
                }

                // Player Characters Section
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.character_sheet),
                            contentDescription = "Characters",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Player Characters (${uiState.user.characters.size})",
                            style = MaterialTheme.typography.headlineMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }

                if (uiState.user.characters.isEmpty()) {
                    item {
                        EmptySectionCard(message = "No player characters created yet. Build a new hero for your next journey!")
                    }
                } else {
                    items(
                        items = uiState.user.characters,
                        key = { it.id }
                    ) { character ->
                        ProfileCharacterCard(
                            character = character,
                            onClick = { onCharacterClick(character) }
                        )
                    }
                }

                // Account Settings & Actions
                item {
                    ProfileAccountActionsCard(
                        onChangePasswordClick = onChangePasswordClick,
                        onLogoutClick = onLogoutClick
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileHeaderCard(
    uiState: ProfileUiState,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelEditClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAvatarSelect: (UserAvatars) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Action Buttons Row (Top-Right)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!uiState.isEditing) {
                    IconButton(onClick = onEditClick) {
                        Icon(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = "Edit Profile",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick = onCancelEditClick) {
                            Text("Cancel")
                        }
                        Button(onClick = onSaveClick) {
                            Text("Save")
                        }
                    }
                }
            }

            // Avatar Display
            Box(contentAlignment = Alignment.BottomEnd) {
                Image(
                    painter = painterResource(uiState.selectedAvatar.avatar),
                    contentDescription = "User Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(96.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                )
            }

            // Avatar Selection options when editing
            AnimatedVisibility(visible = uiState.isEditing) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                        text = "Choose Avatar:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        UserAvatars.entries.forEach { avatar ->
                            val isSelected = uiState.selectedAvatar == avatar
                            Image(
                                painter = painterResource(avatar.avatar),
                                contentDescription = avatar.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = if (isSelected) 3.dp else 1.dp,
                                        color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                        shape = CircleShape
                                    )
                                    .clickable { onAvatarSelect(avatar) }
                            )
                        }
                    }
                }
            }

            // Name & Email Fields or Display
            if (!uiState.isEditing) {
                Text(
                    text = uiState.user.name.ifBlank { "Adventurer" },
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
                if (uiState.user.email.isNotBlank()) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.secondaryContainer
                    ) {
                        Text(
                            text = uiState.user.email,
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }
            } else {
                OutlinedTextField(
                    value = uiState.editName,
                    onValueChange = onNameChange,
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )

                OutlinedTextField(
                    value = uiState.editEmail,
                    onValueChange = onEmailChange,
                    label = { Text("Email") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}

@Composable
private fun ProfileStatsCard(
    campaignsCount: Int,
    charactersCount: Int
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$campaignsCount",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Campaigns",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Surface(
                modifier = Modifier
                    .height(40.dp)
                    .width(1.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            ) {}

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "$charactersCount",
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Characters",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun ProfileBioCard(
    uiState: ProfileUiState,
    onDescriptionChange: (String) -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Adventurer's Bio",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            if (!uiState.isEditing) {
                Text(
                    text = uiState.user.description.ifBlank { "No bio provided yet. Edit your profile to share your backstory and playstyle!" },
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                OutlinedTextField(
                    value = uiState.editDescription,
                    onValueChange = onDescriptionChange,
                    label = { Text("Bio / Backstory") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 6,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }
}

@Composable
private fun ProfileCampaignCard(
    campaign: Campaign,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = campaign.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Text(
                        text = campaign.status.name.replace("_", " "),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            if (campaign.description.isNotBlank()) {
                Text(
                    text = campaign.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GM: ${campaign.gm.name}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = "${campaign.players.size} / ${campaign.maxPlayerCount} Players",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProfileCharacterCard(
    character: PlayerCharacter,
    onClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (character.portrait != null) {
                Image(
                    bitmap = character.portrait,
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                )
            } else {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(64.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            painter = painterResource(Res.drawable.profile),
                            contentDescription = character.name,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (character.description.isNotBlank()) {
                    Text(
                        text = character.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                if (character.themes.isNotEmpty()) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        character.themes.take(3).forEach { theme ->
                            Surface(
                                shape = RoundedCornerShape(4.dp),
                                color = MaterialTheme.colorScheme.secondaryContainer
                            ) {
                                Text(
                                    text = theme.name.name,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptySectionCard(message: String) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProfileAccountActionsCard(
    onChangePasswordClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    OutlinedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Account Settings",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onChangePasswordClick,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Change Password")
                }

                Button(
                    onClick = onLogoutClick,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error,
                        contentColor = MaterialTheme.colorScheme.onError
                    )
                ) {
                    Text("Logout")
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileRoutePreview() {
    TTRPGAppTheme {
        val sampleUser = User(
            id = 1,
            name = "Elminster the Archmage",
            email = "elminster@shadowdale.realm",
            password = "",
            description = "Veteran Dungeon Master and Spellcaster with a fondness for mysterious quests and legendary artifacts.",
            campaigns = emptyList(),
            characters = emptyList()
        )
        ProfileRoute(
            uiState = ProfileUiState(
                user = sampleUser,
                selectedAvatar = UserAvatars.AVATAR1
            )
        )
    }
}

@Preview
@Composable
fun ProfileRouteEditPreview() {
    TTRPGAppTheme {
        val sampleUser = User(
            id = 1,
            name = "Elminster the Archmage",
            email = "elminster@shadowdale.realm",
            password = "",
            description = "Veteran Dungeon Master and Spellcaster.",
            campaigns = emptyList(),
            characters = emptyList()
        )
        ProfileRoute(
            uiState = ProfileUiState(
                user = sampleUser,
                selectedAvatar = UserAvatars.AVATAR2,
                isEditing = true,
                editName = "Elminster",
                editEmail = "elminster@shadowdale.realm",
                editDescription = "Veteran Dungeon Master and Spellcaster."
            )
        )
    }
}
