package com.example.myttrpgapp.presentation.characterSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.presentation.characterSheet.common.Counter
import com.example.myttrpgapp.presentation.characterSheet.common.StatusComposable
import com.example.myttrpgapp.presentation.characterSheet.common.TagComposable
import com.example.myttrpgapp.presentation.characterSheet.common.ThemeComposable
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.edit
import myttrpgapp.shared.generated.resources.status_selected
import org.jetbrains.compose.resources.painterResource

@Composable
fun PlayCharacterRoute(
    modifier: Modifier = Modifier,
    uiState: com.example.myttrpgapp.presentation.characterSheet.CharacterSheetUiState,
    onEvent: (com.example.myttrpgapp.presentation.characterSheet.CharacterSheetEvent) -> Unit,
    onEditCLick: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.character.portrait != null) {
                Image(
                    bitmap = uiState.character.portrait,
                    contentDescription = "Portrait of ${uiState.character.name}",
                    modifier = Modifier.weight(1f)
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = uiState.character.name
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
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onEditCLick() },
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.edit),
                    contentDescription = "Edit character"
                )
            }
        }



        //Description
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
                Text(
                    text = uiState.character.description,
                    modifier = Modifier.padding(top = 8.dp),
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
                Text(
                    text = "Quintessence",
                    style = MaterialTheme.typography.displaySmall,
                )
                if (uiState.character.quintessence.isEmpty()) {
                    Text(
                        text = "No quintessence yet",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
                uiState.character.quintessence.forEach { quintessence ->
                    _root_ide_package_.com.example.myttrpgapp.presentation.characterSheet.QuintessenceComposable(
                        quintessence = quintessence,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        //Fellowship theme
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

        // All character themes
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            uiState.character.themes.forEach { theme ->
                ThemeComposable(
                    theme = theme,
                    modifier = Modifier.weight(1f),
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
            }
            // Backpack theme
            OutlinedCard(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Backpack",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        uiState.character.backpackTags.forEach { tag ->
                            TagComposable(
                                tag = tag,
                                onClick = {
                                    onEvent(
                                        CharacterSheetEvent.OnBackpackTagClick(
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
                    Text(
                        text = "Story tags and statuses",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    if (uiState.character.storyTags.isEmpty() && uiState.character.status.isEmpty()) {
                        Text(
                            text = "No story tags and statuses yet",
                            modifier = Modifier.padding(8.dp)
                        )
                    } else {
                        FlowRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            uiState.character.storyTags.forEach { tag ->
                                TagComposable(
                                    tag = tag,
                                    onClick = {
                                        onEvent(
                                            CharacterSheetEvent.OnBackpackTagClick(
                                                tag
                                            )
                                        )
                                    }
                                )
                            }
                            uiState.character.status.forEach { status ->
                                StatusComposable(
                                    status = status,
                                    modifier = Modifier,
                                    onStatusClick = {
                                        onEvent(
                                            CharacterSheetEvent.OnStatusClick(
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

@Composable
fun QuintessenceComposable(
    modifier: Modifier = Modifier,
    quintessence: com.example.myttrpgapp.domain.model.Quintessence
) {
    val lineHeight = with(LocalDensity.current) {
        MaterialTheme.typography.bodyMedium.lineHeight.toDp()
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.status_selected),
            contentDescription = null,
            modifier = Modifier.height(lineHeight)
        )
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(
                text = quintessence.name,
                style = MaterialTheme.typography.displayMedium
            )
            Text(
                text = quintessence.description,
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 2000)
@Composable
fun CharacterRoutePreview() {
    TTRPGAppTheme {
        _root_ide_package_.com.example.myttrpgapp.presentation.characterSheet.PlayCharacterRoute(
            modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp),
            uiState = _root_ide_package_.com.example.myttrpgapp.presentation.characterSheet.CharacterSheetUiState(
                character = _root_ide_package_.com.example.myttrpgapp.domain.model.Character(
                    id = 1,
                    portrait = null,
                    name = "Valeros",
                    description = "A brave warrior",
                    promise = 3,
                    fellowshipTheme = _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Theme(
                        id = 1,
                        name = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                            id = 1,
                            name = "Fellowship",
                            tagState = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                            tagCategory = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                        ),
                        group = "Fellowship",
                        might = _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Might.ORIGIN,
                        description = "Our shared bond",
                        quest = "Protect the realm",
                        powerTags = listOf(
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                2,
                                "Comrades in Arms",
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                            )
                        ),
                        weaknessTags = listOf(
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                3,
                                "Shared Burden",
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.WEAKNESS
                            )
                        ),
                        milestoneCount = 1,
                        improveCount = 2,
                        abandonCount = 0,
                        specialImprovements = emptyList()
                    ),
                    themes = listOf(
                        _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Theme(
                            id = 2,
                            name = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                id = 4,
                                name = "Firestarter",
                                tagState = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                                tagCategory = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                            ),
                            group = "Magic",
                            might = _root_ide_package_.com.example.myttrpgapp.domain.model.themes.Might.ORIGIN,
                            description = "Master of flames and heat",
                            quest = "Learn the truth behind the eternal flame",
                            powerTags = listOf(
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                    5,
                                    "Fireball",
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                                ),
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                    6,
                                    "Heat Resistance",
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.SELECTED,
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                                )
                            ),
                            weaknessTags = listOf(
                                _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                                    7,
                                    "Water Weakness",
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                                    _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.WEAKNESS
                                )
                            ),
                            milestoneCount = 2,
                            improveCount = 1,
                            abandonCount = 0,
                            specialImprovements = emptyList()
                        )
                    ),
                    backpackTags = listOf(
                        _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                            8,
                            "Healing Potion",
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.SELECTED,
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.BACKPACK
                        ),
                        _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                            9,
                            "Rope & Hook",
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.BACKPACK
                        )
                    ),
                    storyTags = listOf(
                        _root_ide_package_.com.example.myttrpgapp.domain.model.tags.Tag(
                            10,
                            "Marked by the Cult",
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                            _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagCategory.POWER
                        )
                    ),
                    status = listOf(
                        _root_ide_package_.com.example.myttrpgapp.domain.model.statuses.Status(
                            id = 1,
                            name = "Wounded",
                            tagState = _root_ide_package_.com.example.myttrpgapp.domain.model.tags.TagState.UNSELECTED,
                            markedValues = listOf(1, 2)
                        )
                    ),
                    relationships = emptyMap(),
                    quintessence = emptyList()
                )
            ),
            onEvent = {},
            onEditCLick = {}
        )
    }
}


