package com.sedsoftware.remindies.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme

@Composable
fun PeriodSelector(selected: RemindiePeriod) {
    Row {
        Column {
            Text(RemindiePeriod.NONE.str)
            Text(RemindiePeriod.HOURLY.str)
        }
        Column {
            Text(RemindiePeriod.DAILY.str)
            Text(RemindiePeriod.WEEKLY.str)
        }
        Column {
            Text(RemindiePeriod.MONTHLY.str)
            Text(RemindiePeriod.YEARLY.str)
        }
    }
}

@Preview
@Composable
fun PeriodSelectorPreview() {
    RemindiesUITheme {
        PeriodSelector(RemindiePeriod.MONTHLY)
    }
}
