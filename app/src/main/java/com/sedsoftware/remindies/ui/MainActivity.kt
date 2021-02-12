package com.sedsoftware.remindies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import com.sedsoftware.remindies.domain.RemindiePeriod
import com.sedsoftware.remindies.domain.States
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindiesUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AddNewItem(addNewState)
                }
            }
        }
    }
}

private val addNewState = States.AddNew(
    title = "Task name",
    shot = LocalDateTime.now(),
    periodical = true,
    saveEnabled = true,
    period = RemindiePeriod.DAILY,
    each = 3
)

@Composable
@Preview(showBackground = true)
fun AddNewItemPreview() {
    RemindiesUITheme {
        AddNewItem(addNewState)
    }
}

@Composable
fun AddNewItem(state: States.AddNew) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            value = state.title,
            onValueChange = {},
            placeholder = { Text("Task name") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            Column {
                Text(
                    text = "03.04.2021"
                )
                PeriodicalChecker(
                    enabled = true,
                    each = 10
                )
            }

            Column {
                Text(
                    text = "12:34"
                )
                PeriodSelector(
                    selected = RemindiePeriod.WEEKLY
                )
            }
        }
    }
}
