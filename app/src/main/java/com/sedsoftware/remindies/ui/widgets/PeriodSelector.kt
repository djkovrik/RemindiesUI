package com.sedsoftware.remindies.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme

@Composable
fun PeriodSelector(selected: RemindiePeriod, modifier: Modifier = Modifier) {
    Row {
        Column {
            Text(text = RemindiePeriod.NONE.str)
            Text(text = RemindiePeriod.HOURLY.str)
        }
        Column {
            Text(text = RemindiePeriod.DAILY.str)
            Text(text = RemindiePeriod.WEEKLY.str)
        }
        Column {
            Text(text = RemindiePeriod.MONTHLY.str)
            Text(text = RemindiePeriod.YEARLY.str)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PeriodSelectorPreview() {
    RemindiesUITheme {
        PeriodSelector(RemindiePeriod.MONTHLY, Modifier)
    }
}
