package com.liau.jetgithub.ui.preference

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.R

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Suppress("FunctionName")
@Composable
fun HomeScreen(titlePage: String) {
    val context = (LocalContext.current) as Activity
    val lastBackPressed: MutableState<Long> = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = titlePage,
            modifier = Modifier.padding(16.dp)
        )
    }

    BackHandler {
        val currentTimes = System.currentTimeMillis()
        if (lastBackPressed.value + 1000 > currentTimes) {
            context.finish()
        } else {
            lastBackPressed.value = currentTimes
            Toast.makeText(
                context,
                context.getString(R.string.press_again_exit),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}