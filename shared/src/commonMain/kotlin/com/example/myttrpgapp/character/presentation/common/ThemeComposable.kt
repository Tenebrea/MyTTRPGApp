package com.example.myttrpgapp.character.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import com.example.myttrpgapp.character.domain.model.Tag
import com.example.myttrpgapp.character.domain.model.TagCategory
import com.example.myttrpgapp.character.domain.model.TagState
import com.example.myttrpgapp.character.domain.model.Theme
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.status_selectable
import myttrpgapp.shared.generated.resources.status_selected
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemeComposable(
    theme: Theme,
    modifier: Modifier = Modifier,
    onPowerTagClick: (Tag) -> Unit,
    onWeaknessTagClick: (Tag) -> Unit,
    onImproveClick: (Int) -> Unit,
    onMilestoneClick: (Int) -> Unit,
    onAbandonClick: (Int) -> Unit,
) {
    Box(modifier = modifier) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = theme.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

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
                        Text(
                            text = theme.description,
                            modifier = Modifier.padding(top = 8.dp),
                            maxLines = 5,
                        )
                    }
                }

                if (theme.powerTags.isNotEmpty() || theme.weaknessTags.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        theme.powerTags.forEach { tag ->
                            TagComposable(
                                tag = tag,
                                onClick = { onPowerTagClick(tag) }
                            )
                        }
                        theme.weaknessTags.forEach { tag ->
                            TagComposable(
                                tag = tag,
                                onClick = { onWeaknessTagClick(tag) }
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Counter(
                            count = theme.milestoneCount,
                            name = "Milestone",
                            modifier = Modifier.weight(1f),
                            onClick = onMilestoneClick
                        )
                        Counter(
                            count = theme.abandonCount,
                            name = "Abandon",
                            modifier = Modifier.weight(1f),
                            onClick = onAbandonClick
                        )
                    }
                    Counter(
                        count = theme.improveCount,
                        name = "Improve",
                        onClick = onImproveClick
                    )
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
                        Text(
                            text = theme.quest,
                            modifier = Modifier.padding(top = 8.dp),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(
    count: Int,
    name: String,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit
) {
    val lineHeightDp = with(LocalDensity.current) {
        MaterialTheme.typography.bodyMedium.lineHeight.toDp()
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = name,
            modifier = Modifier.padding(16.dp)
        )
        Row {
            for (i in 1..3) {
                Image(
                    painter = if (i <= count) painterResource(Res.drawable.status_selected) else painterResource(
                        Res.drawable.status_selectable),
                    contentDescription = "Current $name level $i",
                    modifier = Modifier.clickable { onClick(i) }.height(lineHeightDp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ThemeComposablePreview() {
    TTRPGAppTheme {
        ThemeComposable(
            theme = Theme(
                id = 1,
                name = "Firestarter",
                description = "Master of flames and heat.",
                quest = "Learn the truth behind the eternal flame.",
                powerTags = listOf(
                    Tag(
                        id = 1,
                        name = "Fireball",
                        description = "Casts a fireball",
                        tagState = TagState.UNSELECTED,
                        tagCategory = TagCategory.POWER
                    ),
                    Tag(
                        id = 2,
                        name = "Heat Resistance",
                        description = "Resistant to fire",
                        tagState = TagState.SELECTED,
                        tagCategory = TagCategory.POWER
                    )
                ),
                weaknessTags = listOf(
                    Tag(
                        id = 3,
                        name = "Water Weakness",
                        description = "Weak against water",
                        tagState = TagState.UNSELECTED,
                        tagCategory = TagCategory.WEAKNESS
                    )
                ),
                milestoneCount = 2,
                improveCount = 1,
                abandonCount = 0,
                specialImprovements = emptyList()
            ),
            onPowerTagClick = {},
            onWeaknessTagClick = {},
            onImproveClick = {},
            onMilestoneClick = {},
            onAbandonClick = {}
        )
    }
}