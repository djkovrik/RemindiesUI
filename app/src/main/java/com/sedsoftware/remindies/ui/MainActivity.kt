package com.sedsoftware.remindies.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.sedsoftware.remindies.ui.ui.RemindiesUITheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RemindiesUITheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                }
            }
        }
    }
}


//@Composable
//@Preview(showBackground = true)
//fun AddNewItemPreview() {
//    RemindiesUITheme {
//        NewItem(addNewState)
//    }
//}
