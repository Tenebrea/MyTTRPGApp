package com.example.myttrpgapp.character.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.SelectedPowerTagColor
import com.example.myhabitapp.ui.theme.SelectedWeaknessTagColor
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.character.domain.model.Tag
import com.example.myttrpgapp.character.domain.model.TagCategory
import com.example.myttrpgapp.character.domain.model.TagState
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.burning
import org.jetbrains.compose.resources.painterResource

@Composable
fun TagComposable(
    tag: Tag,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .clickable { onClick() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                if (tag.tagState == TagState.SELECTED) {
                    when (tag.tagCategory) {
                        TagCategory.POWER -> SelectedPowerTagColor
                        TagCategory.WEAKNESS -> SelectedWeaknessTagColor
                        TagCategory.STORY -> SelectedPowerTagColor
                        TagCategory.BACKPACK -> SelectedPowerTagColor
                    }
                } else if(tag.tagState == TagState.WEAKENED) {
                    SelectedWeaknessTagColor
                } else {
                    when (tag.tagCategory) {
                        TagCategory.POWER -> MaterialTheme.colorScheme.primary
                        TagCategory.WEAKNESS -> MaterialTheme.colorScheme.secondary
                        TagCategory.STORY -> MaterialTheme.colorScheme.primary
                        TagCategory.BACKPACK -> MaterialTheme.colorScheme.primary
                    }
                }
            ),
            elevation = CardDefaults.cardElevation(4.dp),
            border = CardDefaults.outlinedCardBorder()
        ) {
            Row {
                if (tag.tagState == TagState.BURNING) {
                    val lineHeightDp = with(LocalDensity.current) {
                        MaterialTheme.typography.bodyMedium.lineHeight.toDp()
                    }
                    Image(
                        painter = painterResource(Res.drawable.burning),
                        contentDescription = "Burning Tag",
                        modifier = Modifier.padding(start = 4.dp, top = 4.dp, bottom = 4.dp).height(lineHeightDp)
                    )
                }
                Text(
                    text = tag.name,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium
                        .plus(TextStyle(textDecoration = if (tag.tagState == TagState.SCRATCHED) TextDecoration.LineThrough else null)),
                    color = when (tag.tagCategory) {
                        TagCategory.POWER -> MaterialTheme.colorScheme.onPrimary
                        TagCategory.WEAKNESS -> MaterialTheme.colorScheme.onSecondary
                        TagCategory.STORY -> MaterialTheme.colorScheme.onPrimary
                        TagCategory.BACKPACK -> MaterialTheme.colorScheme.onPrimary
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun TagPreview() {
    TTRPGAppTheme(darkTheme = true) {
        TagComposable(
            Tag(
                1,
                "Some pretty long text",
                "test",
                TagState.BURNING,
                TagCategory.POWER),
            onClick = {},
        )
    }
}