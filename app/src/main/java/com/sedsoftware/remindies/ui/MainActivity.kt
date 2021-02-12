package com.sedsoftware.remindies.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
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
fun AddNewItem(state: States.AddNew) {

}

