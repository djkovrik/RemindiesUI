package com.sedsoftware.remindies.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme

@Composable
fun PeriodicalChecker(enabled: Boolean, each: Int, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = enabled,
            onCheckedChange = {}
        )

        Text(text = "periodical")

        TextField(
            value = each.toString(),
            onValueChange = {},
            placeholder = { Text("each") },
            singleLine = true
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PeriodicalCheckerPreview() {
    RemindiesUITheme {
        PeriodicalChecker(true, 10, Modifier)
    }
}
