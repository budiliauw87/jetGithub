package com.liau.jetgithub.ui.preference

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
    val context = LocalContext.current as Activity
    val lastBackPressed: MutableState<Long> = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titlePage,
            modifier = Modifier.padding(16.dp)
        )
    }

    BackHandler {
        val currentTimes = System.currentTimeMillis()
        if (lastBackPressed.value+1000 > currentTimes) {
            context.finish()
        } else {
            lastBackPressed.value = currentTimes
            Toast.makeText(context, context.getString(R.string.press_again_exit), Toast.LENGTH_SHORT).show()
        }

    }
}