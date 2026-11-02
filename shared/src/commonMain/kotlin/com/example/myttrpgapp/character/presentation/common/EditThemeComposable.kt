package com.example.myttrpgapp.character.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.character.domain.model.tags.Tag
import com.example.myttrpgapp.character.domain.model.tags.TagCategory
import com.example.myttrpgapp.character.domain.model.tags.TagState
import com.example.myttrpgapp.character.domain.model.themes.Might
import com.example.myttrpgapp.character.domain.model.themes.SpecialImprovement
import com.example.myttrpgapp.character.domain.model.themes.Theme
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.add
import myttrpgapp.shared.generated.resources.adventure_theme
import myttrpgapp.shared.generated.resources.greatness_theme
import myttrpgapp.shared.generated.resources.origin_theme
import myttrpgapp.shared.generated.resources.remove
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditThemeComposable(
    modifier: Modifier = Modifier,
    onNameChange: (String) -> Unit,
    theme: Theme,
    onAddPowerTag: () -> Unit,
    onAddWeaknessTag: () -> Unit,
    onRemovePowerTag: (Tag) -> Unit,
    onRemoveWeaknessTag: (Tag) -> Unit,
    onPowerTagChange: (Tag, String) -> Unit,
    onWeaknessTagChange: (Tag, String) -> Unit,
    onQuestChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onThemeMightChange: (Might) -> Unit,
    onAddSpecialImprovement: () -> Unit,
    onSpecialImprovementNameChange: (SpecialImprovement, String) -> Unit,
    onSpecialImprovementDescriptionChange: (SpecialImprovement, String) -> Unit,
    onRemoveSpecialImprovement: (SpecialImprovement) -> Unit,
    onThemeGroupChange: (String) -> Unit,
    onThemeRemove: () -> Unit,
    onThemeSave: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(
            value = theme.name.name,
            onValueChange = onNameChange,
            placeholder = { Text("Theme name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                value = theme.group,
                onValueChange = onThemeGroupChange,
                placeholder = { Text("Theme group") },
                modifier = Modifier.weight(1f),
                maxLines = 1
            )

            var expandedState by remember{ mutableStateOf(false) }
            OutlinedCard(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.fillMaxWidth().clickable { expandedState = true }) {
                    Text(
                        text = theme.might.name,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    DropdownMenu(
                        expanded = expandedState,
                        onDismissRequest = { expandedState = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Origin") },
                            onClick = {
                                onThemeMightChange(Might.GREATNESS)
                                expandedState = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Adventure") },
                            onClick = {
                                onThemeMightChange(Might.GREATNESS)
                                expandedState = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Greatness") },
                            onClick = {
                                onThemeMightChange(Might.GREATNESS)
                                expandedState = false
                            }
                        )
                    }
                }
            }
        }


        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Description",
                    style = MaterialTheme.typography.displaySmall,
                )
                TextField(
                    value = theme.description,
                    onValueChange = onDescriptionChange
                )
            }
        }
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Power tags",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    IconButton(
                        onClick = onAddPowerTag
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.add),
                            contentDescription = "Add power tag"
                        )
                    }
                }

                theme.powerTags.forEach { tag ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextField(
                            value = tag.name,
                            onValueChange = { onPowerTagChange(tag, it) },
                            maxLines = 1
                        )
                        IconButton(
                            onClick = { onRemovePowerTag(tag) }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.remove),
                                contentDescription = "Remove ${tag.name} power tag"
                            )
                        }
                    }
                }
            }
        }
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Weakness tags",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    IconButton(
                        onClick = onAddWeaknessTag
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.add),
                            contentDescription = "Add weakness tag"
                        )
                    }
                }

                theme.weaknessTags.forEach { tag ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextField(
                            value = tag.name,
                            onValueChange = { onWeaknessTagChange(tag, it) },
                            maxLines = 1
                        )
                        IconButton(
                            onClick = { onRemoveWeaknessTag(tag) }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.remove),
                                contentDescription = "Remove ${tag.name} weakness tag"
                            )
                        }
                    }
                }
            }
        }
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Quest",
                    style = MaterialTheme.typography.displaySmall,
                )
                TextField(
                    value = theme.quest,
                    onValueChange = onQuestChange,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Special improvements",
                        style = MaterialTheme.typography.displaySmall,
                    )
                    IconButton(
                        onClick = onAddSpecialImprovement
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.add),
                            contentDescription = "Add special improvement"
                        )
                    }
                }

                theme.specialImprovements.forEach { specialImprovement ->
                    Row {
                        Column {
                            TextField(
                                value = specialImprovement.name,
                                onValueChange = { onSpecialImprovementNameChange(specialImprovement, it) },
                                maxLines = 1,
                                modifier = Modifier.fillMaxWidth()
                            )
                            TextField(
                                value = specialImprovement.description,
                                onValueChange = { onSpecialImprovementDescriptionChange(specialImprovement, it) },
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        IconButton(
                            onClick = { onRemoveSpecialImprovement(specialImprovement) }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.remove),
                                contentDescription = "Remove ${specialImprovement.name} special improvement"
                            )
                        }
                    }
                }
            }
        }


        Button(
            onClick = onThemeSave,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Save theme"
            )
        }
        OutlinedButton(
            onClick = onThemeRemove,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Delete theme"
            )
        }
    }
}

@Preview(showBackground = true, heightDp = 1100)
@Composable
fun EditThemeComposablePreview() {
    TTRPGAppTheme {
        EditThemeComposable(
            theme = Theme(
                id = 1,
                name = Tag(
                    id = 0,
                    name = "Firestarter",
                    tagState = TagState.UNSELECTED,
                    tagCategory = TagCategory.POWER
                ),
                description = "Master of flames and heat.",
                quest = "Learn the truth behind the eternal flame.",
                powerTags = listOf(
                    Tag(
                        id = 1,
                        name = "Fireball",
                        tagState = TagState.UNSELECTED,
                        tagCategory = TagCategory.POWER
                    ),
                    Tag(
                        id = 2,
                        name = "Heat Resistance",
                        tagState = TagState.SELECTED,
                        tagCategory = TagCategory.POWER
                    )
                ),
                weaknessTags = listOf(
                    Tag(
                        id = 3,
                        name = "Water Weakness",
                        tagState = TagState.UNSELECTED,
                        tagCategory = TagCategory.WEAKNESS
                    )
                ),
                milestoneCount = 2,
                improveCount = 1,
                abandonCount = 0,
                specialImprovements = listOf(
                    SpecialImprovement(
                        id = 1,
                        name = "Black sheep",
                        description = "You're allowed to do everything"
                    )
                ),
                group = "Magic",
                might = Might.ORIGIN
            ),
            onNameChange = {},
            onAddPowerTag = {},
            onAddWeaknessTag = {},
            onRemovePowerTag = {},
            onRemoveWeaknessTag = {},
            onPowerTagChange = { _, _ -> },
            onWeaknessTagChange = { _, _ -> },
            onQuestChange = {},
            onDescriptionChange = {},
            onThemeMightChange = {},
            onAddSpecialImprovement = {},
            onSpecialImprovementNameChange = { _, _ -> },
            onSpecialImprovementDescriptionChange = { _, _ -> },
            onRemoveSpecialImprovement = {},
            onThemeGroupChange = {},
            onThemeRemove = {},
            onThemeSave = {},
            modifier = Modifier.scrollable(rememberScrollState(), orientation = Orientation.Vertical)
        )
    }
}