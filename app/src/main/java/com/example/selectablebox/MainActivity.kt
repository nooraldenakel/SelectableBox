package com.example.selectablebox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.selectablebox.ui.theme.SelectableBox
import com.example.selectablebox.ui.theme.SelectableBoxTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelectableBoxTheme {
                val selected = remember { mutableStateOf(false) }
                val selected2 = remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(75.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SelectableBox(
                        selected = selected.value,
                        title = "Noor Alden",
                        onClick = {
                            selected.value = !selected.value
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SelectableBox(
                        selected = selected2.value,
                        title = "Noor Alden",
                        subtitle = "The articles starts with “ — ” aren’t main topics but they support the overall understanding of other articles in the course. It’s not recommended to skip any of them while following along.",
                        onClick = {
                            selected2.value = !selected2.value
                        }
                    )


                }
            }
        }
    }
}