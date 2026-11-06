package com.example.myttrpgapp.presentation.characterSheet.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.domain.model.statuses.Status
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.remove
import myttrpgapp.shared.generated.resources.status_selectable
import myttrpgapp.shared.generated.resources.status_selected
import org.jetbrains.compose.resources.painterResource

@Composable
fun EditStatusComposable(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    onRemove: () -> Unit,
    onLevelClick: (Int) -> Unit,
    status: Status,
) {
    val lineHeightDp = with(LocalDensity.current) {
        MaterialTheme.typography.bodyMedium.lineHeight.toDp()
    }
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = status.name,
                onValueChange = { onValueChanged(it) },
                maxLines = 1,
                placeholder = { Text(text = "Status name") },
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = onRemove
            ) {
                Icon(
                    painter = painterResource(Res.drawable.remove),
                    contentDescription = "Remove ${status.name} status"
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 1..6) {
                Image(
                    painter = if (i in status.markedValues) painterResource(Res.drawable.status_selected) else painterResource(Res.drawable.status_selectable),
                    contentDescription = "Status level $i selected",
                    modifier = Modifier
                        .padding(4.dp)
                        .height(lineHeightDp)
                        .clickable { onLevelClick(i) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditStatusComposablePreview() {
    TTRPGAppTheme {
        EditStatusComposable(
            status = Status(
                id = 1,
                name = "Wounded",
                markedValues = listOf(1, 2)
            ),
            onValueChanged = {},
            onRemove = {},
            onLevelClick = {}
        )
    }
}