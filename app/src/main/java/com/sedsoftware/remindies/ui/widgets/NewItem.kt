package com.sedsoftware.remindies.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.States

//@Composable
//fun NewItem(state: States.AddNew) {
//    Column(modifier = Modifier.fillMaxWidth()) {
//
//        OutlinedTextField(
//            value = state.title,
//            onValueChange = {},
//            placeholder = { Text("Task name") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Row {
//            Column {
//                Text(
//                    text = "03.04.2021"
//                )
//                PeriodicalChecker(
//                    enabled = true,
//                    each = 10
//                )
//            }
//
//            Column {
//                Text(
//                    text = "12:34"
//                )
//                PeriodSelector(
//                    selected = RemindiePeriod.WEEKLY
//                )
//            }
//        }
//    }
//}
