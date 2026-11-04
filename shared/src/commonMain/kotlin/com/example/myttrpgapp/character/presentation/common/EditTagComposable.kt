package com.example.myttrpgapp.character.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.character.domain.model.tags.Tag
import com.example.myttrpgapp.character.domain.model.tags.TagCategory
import com.example.myttrpgapp.character.domain.model.tags.TagState
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.remove
import org.jetbrains.compose.resources.painterResource
import java.util.Locale

@Composable
fun EditTagComposable(
    modifier: Modifier = Modifier,
    tag: Tag,
    onValueChanged: (String) -> Unit,
    onRemoveTag: (Tag) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        TextField(
            value = tag.name,
            onValueChange = { onValueChanged(it) },
            maxLines = 1,
            placeholder = { Text(text = "Tag name") },
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { onRemoveTag(tag) }
        ) {
            Icon(
                painter = painterResource(Res.drawable.remove),
                contentDescription = "Remove ${tag.name} ${
                    tag.tagCategory.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    }
                } tag"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditTagComposablePreview() {
    TTRPGAppTheme {
        EditTagComposable(
            tag = Tag(
                id = 1,
                name = "Fireball",
                tagState = TagState.UNSELECTED,
                tagCategory = TagCategory.POWER
            ),
            onValueChanged = {},
            onRemoveTag = {}
        )
    }
}