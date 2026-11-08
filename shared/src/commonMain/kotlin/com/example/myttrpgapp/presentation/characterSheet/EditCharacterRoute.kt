package com.example.myttrpgapp.presentation.characterSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.domain.model.tags.Tag
import com.example.myttrpgapp.domain.model.tags.TagCategory
import com.example.myttrpgapp.domain.model.tags.TagState
import com.example.myttrpgapp.domain.model.themes.Might
import com.example.myttrpgapp.domain.model.themes.Theme
import com.example.myttrpgapp.presentation.characterSheet.common.Counter
import com.example.myttrpgapp.presentation.characterSheet.common.EditStatusComposable
import com.example.myttrpgapp.presentation.characterSheet.common.EditTagComposable
import com.example.myttrpgapp.presentation.characterSheet.common.ThemeComposable
import com.example.myttrpgapp.presentation.util.toImageBitmap
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.add
import myttrpgapp.shared.generated.resources.edit
import myttrpgapp.shared.generated.resources.placeholder_image
import myttrpgapp.shared.generated.resources.play_icon
import myttrpgapp.shared.generated.resources.remove
import myttrpgapp.shared.generated.resources.status
import myttrpgapp.shared.generated.resources.tag
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditCharacterRoute(
    modifier: Modifier = Modifier,
    uiState: CharacterSheetUiState,
    onEvent: (CharacterSheetEvent) -> Unit,
    onFinishEditingClick: () -> Unit,
    onEditTheme: (Theme) -> Unit
) {
    lateinit var imageBitmap: ImageBitmap
    if (uiState.character.portrait == null) {
        val placeholderImage = painterResource(Res.drawable.placeholder_image)
        val placeholderImageSize = placeholderImage.intrinsicSize
        imageBitmap = placeholderImage.toImageBitmap(
            size = placeholderImageSize,
            density = LocalDensity.current
        )
    } else {
        imageBitmap = uiState.character.portrait
    }

    Column(modifier = modifier) {
        Row(
            Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = imageBitmap,
                contentDescription = "Portrait of ${uiState.character.name}",
                modifier = Modifier
                    .size(64.dp)
                    .clickable { onEvent(CharacterSheetEvent.OnCharacterPortraitChange) }
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                TextField(
                    value = uiState.character.name,
                    onValueChange = { onEvent(CharacterSheetEvent.OnCharacterNameChange(it)) },
                    maxLines = 1,
                    placeholder = { Text(text = "Character name") },
                    modifier = Modifier
                )
                Counter(
                    count = uiState.character.promise,
                    maxCount = 5,
                    name = "Promise",
                    modifier = Modifier,
                    onClick = {
                        onEvent(
                            CharacterSheetEvent.OnPromiseClick(
                                it
                            )
                        )
                    }
                )
            }
            IconButton(
                onClick = { onFinishEditingClick() },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.play_icon),
                    contentDescription = "Edit character"
                )
            }
        }
        //Character description
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Character description",
                    style = MaterialTheme.typography.displaySmall,
                )
                TextField(
                    value = uiState.character.description,
                    onValueChange = { onEvent(CharacterSheetEvent.OnCharacterDescriptionChange(it)) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        //Quintessence
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Quintessence",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    IconButton(
                        onClick = { onEvent(CharacterSheetEvent.OnAddQuintessenceClick) }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.add),
                            contentDescription = "Add Quintessence"
                        )
                    }
                }
                uiState.character.quintessence.forEach { quintessence ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            TextField(
                                value = quintessence.name,
                                onValueChange = {
                                    onEvent(
                                        CharacterSheetEvent.OnQuintessenceNameChange(
                                            quintessence,
                                            it
                                        )
                                    )
                                },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Quintessence name") }
                            )
                            TextField(
                                value = quintessence.description,
                                onValueChange = {
                                    onEvent(
                                        CharacterSheetEvent.OnQuintessenceDescriptionChange(
                                            quintessence,
                                            it
                                        )
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Quintessence description") }
                            )
                        }
                        IconButton(
                            onClick = {
                                onEvent(
                                    CharacterSheetEvent.OnRemoveQuintessenceClick(
                                        quintessence
                                    )
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.remove),
                                contentDescription = "Remove ${quintessence.name} special quintessence"
                            )
                        }
                    }
                }
            }
        }

        //Fellowship theme
        Box {
            ThemeComposable(
                theme = uiState.character.fellowshipTheme,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                onPowerTagClick = {
                    onEvent(
                        CharacterSheetEvent.OnPowerTagClick(
                            it,
                            uiState.character.fellowshipTheme
                        )
                    )
                },
                onWeaknessTagClick = {
                    onEvent(
                        CharacterSheetEvent.OnPowerTagClick(
                            it,
                            uiState.character.fellowshipTheme
                        )
                    )
                },
                onImproveClick = {
                    onEvent(
                        CharacterSheetEvent.OnImproveClick(
                            it,
                            uiState.character.fellowshipTheme
                        )
                    )
                },
                onMilestoneClick = {
                    onEvent(
                        CharacterSheetEvent.OnMilestoneClick(
                            it,
                            uiState.character.fellowshipTheme
                        )
                    )
                },
                onAbandonClick = {
                    onEvent(
                        CharacterSheetEvent.OnAbandonClick(
                            it,
                            uiState.character.fellowshipTheme
                        )
                    )
                }
            )
            IconButton(
                onClick = { onEditTheme(uiState.character.fellowshipTheme) },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp, top = 24.dp)
                    .size(48.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.edit),
                    contentDescription = "Edit fellowship theme"
                )
            }
        }

        // All character themes
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.character.themes.forEach { theme ->
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    ThemeComposable(
                        theme = theme,
                        onPowerTagClick = {
                            onEvent(
                                CharacterSheetEvent.OnPowerTagClick(
                                    it,
                                    theme
                                )
                            )
                        },
                        onWeaknessTagClick = {
                            onEvent(
                                CharacterSheetEvent.OnWeaknessTagClick(
                                    it,
                                    theme
                                )
                            )
                        },
                        onImproveClick = {
                            onEvent(
                                CharacterSheetEvent.OnImproveClick(
                                    it,
                                    theme
                                )
                            )
                        },
                        onMilestoneClick = {
                            onEvent(
                                CharacterSheetEvent.OnMilestoneClick(
                                    it,
                                    theme
                                )
                            )
                        },
                        onAbandonClick = {
                            onEvent(
                                CharacterSheetEvent.OnAbandonClick(
                                    it,
                                    theme
                                )
                            )
                        }
                    )
                    IconButton(
                        onClick = { onEditTheme(theme) },
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(48.dp)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.edit),
                            contentDescription = "Edit ${theme.name} theme"
                        )
                    }
                }
            }
            // Backpack theme
            OutlinedCard(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row{
                        Text(
                            text = "Backpack",
                            style = MaterialTheme.typography.displaySmall,
                        )
                        IconButton(
                            onClick = { onEvent(CharacterSheetEvent.OnAddBackpackTagClick) }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.add),
                                contentDescription = "Add backpack tag"
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        uiState.character.backpackTags.forEach { tag ->
                            EditTagComposable(
                                modifier = Modifier.fillMaxWidth(),
                                tag = tag,
                                onValueChanged = {
                                    onEvent(
                                        CharacterSheetEvent.OnBackpackTagChange(
                                            tag,
                                            it
                                        )
                                    )
                                },
                                onRemoveTag = {
                                    onEvent(
                                        CharacterSheetEvent.OnBackpackTagRemove(
                                            tag
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }

            //Story tags and statuses
            OutlinedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row{
                        Text(
                            text = "Story tags and statuses",
                            style = MaterialTheme.typography.displaySmall,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = { onEvent(CharacterSheetEvent.OnAddStoryTagClick) },
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.tag),
                                contentDescription = "Add story tag"
                            )
                        }
                        IconButton(
                            onClick = { onEvent(CharacterSheetEvent.OnAddStatusClick) }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.status),
                                contentDescription = "Add status"
                            )
                        }
                    }

                    if (uiState.character.storyTags.isEmpty() && uiState.character.status.isEmpty()) {
                        Text(
                            text = "No story tags and statuses yet",
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            uiState.character.storyTags.forEach { tag ->
                                EditTagComposable(
                                    tag = tag,
                                    modifier = Modifier.fillMaxWidth(),
                                    onValueChanged = {
                                        onEvent(
                                            CharacterSheetEvent.OnStoryTagChanged(
                                                tag,
                                                it
                                            )
                                        )
                                    },
                                    onRemoveTag = {
                                        onEvent(
                                            CharacterSheetEvent.OnStoryTagRemove(
                                                tag
                                            )
                                        )
                                    }
                                )
                            }
                            uiState.character.status.forEach { status ->
                                EditStatusComposable(
                                    status = status,
                                    modifier = Modifier.fillMaxWidth(),
                                    onValueChanged = {
                                        onEvent(
                                            CharacterSheetEvent.OnStatusChanged(
                                                status,
                                                it
                                            )
                                        )
                                    },
                                    onRemove = {
                                        onEvent(
                                            CharacterSheetEvent.OnStatusRemove(
                                                status
                                            )
                                        )
                                    },
                                    onLevelClick = {
                                        onEvent(
                                            CharacterSheetEvent.OnStatusLevelClick(
                                                status,
                                                it
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
fun EditCharacterRoutePreview() {
    TTRPGAppTheme {
        EditCharacterRoute(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp),
            uiState = CharacterSheetUiState(
                character = com.example.myttrpgapp.domain.model.PlayerCharacter(
                    id = 1,
                    portrait = null,
                    name = "Valeros",
                    description = "A brave warrior",
                    promise = 3,
                    fellowshipTheme = Theme(
                        id = 1,
                        name = Tag(
                            id = 1,
                            name = "Fellowship",
                            tagState = TagState.UNSELECTED,
                            tagCategory = TagCategory.POWER
                        ),
                        group = "Fellowship",
                        might = Might.ORIGIN,
                        description = "Our shared bond",
                        quest = "Protect the realm",
                        powerTags = listOf(
                            Tag(
                                2,
                                "Comrades in Arms",
                                TagState.UNSELECTED,
                                TagCategory.POWER
                            )
                        ),
                        weaknessTags = listOf(
                            Tag(
                                3,
                                "Shared Burden",
                                TagState.UNSELECTED,
                                TagCategory.WEAKNESS
                            )
                        ),
                        milestoneCount = 1,
                        improveCount = 2,
                        abandonCount = 0,
                        specialImprovements = emptyList()
                    ),
                    themes = listOf(
                        Theme(
                            id = 2,
                            name = Tag(
                                id = 4,
                                name = "Firestarter",
                                tagState = TagState.UNSELECTED,
                                tagCategory = TagCategory.POWER
                            ),
                            group = "Magic",
                            might = Might.ORIGIN,
                            description = "Master of flames and heat",
                            quest = "Learn the truth behind the eternal flame",
                            powerTags = listOf(
                                Tag(
                                    5,
                                    "Fireball",
                                    TagState.UNSELECTED,
                                    TagCategory.POWER
                                ),
                                Tag(
                                    6,
                                    "Heat Resistance",
                                    TagState.SELECTED,
                                    TagCategory.POWER
                                )
                            ),
                            weaknessTags = listOf(
                                Tag(
                                    7,
                                    "Water Weakness",
                                    TagState.UNSELECTED,
                                    TagCategory.WEAKNESS
                                )
                            ),
                            milestoneCount = 2,
                            improveCount = 1,
                            abandonCount = 0,
                            specialImprovements = emptyList()
                        )
                    ),
                    backpackTags = listOf(
                        Tag(
                            8,
                            "Healing Potion",
                            TagState.SELECTED,
                            TagCategory.BACKPACK
                        ),
                        Tag(
                            9,
                            "Rope & Hook",
                            TagState.UNSELECTED,
                            TagCategory.BACKPACK
                        )
                    ),
                    storyTags = listOf(
                        Tag(
                            10,
                            "Marked by the Cult",
                            TagState.UNSELECTED,
                            TagCategory.POWER
                        )
                    ),
                    status = listOf(
                        com.example.myttrpgapp.domain.model.statuses.Status(
                            id = 1,
                            name = "Wounded",
                            tagState = TagState.UNSELECTED,
                            markedValues = listOf(1, 2)
                        )
                    ),
                    relationships = emptyMap(),
                    quintessence = emptyList()
                )
            ),
            onEvent = {},
            onFinishEditingClick = {},
            onEditTheme = {}
        )
    }
}