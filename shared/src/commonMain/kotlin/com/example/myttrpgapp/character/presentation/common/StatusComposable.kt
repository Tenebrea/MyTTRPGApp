package com.example.myttrpgapp.character.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myhabitapp.ui.theme.SelectedStatusColor
import com.example.myhabitapp.ui.theme.TTRPGAppTheme
import com.example.myttrpgapp.character.domain.model.Status
import com.example.myttrpgapp.character.domain.model.TagState
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.status_selectable
import myttrpgapp.shared.generated.resources.status_selected
import org.jetbrains.compose.resources.painterResource

@Composable
fun StatusComposable(
    status: Status,
    modifier: Modifier = Modifier,
    onStatusClick: () -> Unit,
    onLevelClick: (Int) -> Unit
) {
    val lineHeightDp = with(LocalDensity.current) {
        MaterialTheme.typography.bodyMedium.lineHeight.toDp()
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(if (status.tagState == TagState.SELECTED) SelectedStatusColor else MaterialTheme.colorScheme.tertiary),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.clickable{ onStatusClick() }
        ) {
            Column {
                Text(
                    text = status.name,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
                Row {
                    for (i in 1..6) {
                        Image(
                            painter = if (i in status.markedValues) painterResource(Res.drawable.status_selected) else painterResource(Res.drawable.status_selectable),
                            contentDescription = "Status level $i selected",
                            modifier = Modifier.padding(4.dp).height(lineHeightDp).clickable { onLevelClick(i) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun StatusComposablePreview() {
    TTRPGAppTheme {
        StatusComposable(
            status = Status(
                id = 1,
                name = "Some status",
                tagState = TagState.UNSELECTED,
                markedValues = listOf(1, 3)
            ),
            onStatusClick = {  },
            onLevelClick = {  }
        )
    }
}