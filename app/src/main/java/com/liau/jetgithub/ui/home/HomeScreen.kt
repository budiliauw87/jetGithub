package com.liau.jetgithub.ui.setting

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.core.di.Injector
import com.liau.jetgithub.core.di.ViewModelFactory
import com.liau.jetgithub.state.UiState
import com.liau.jetgithub.ui.error.ErrorContent
import com.liau.jetgithub.ui.home.HomeViewModel

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Suppress("FunctionName")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository(LocalContext.current))
    )
) {
    val context = (LocalContext.current) as Activity
    val lastBackPressed: MutableState<Long> = remember { mutableStateOf(0) }
    viewModel.uiStateUser.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Text(
                    text = "Loading",
                    modifier = Modifier.padding(16.dp)
                )
                Log.e("HomeScreen", "Loading")
                viewModel.getUser()
            }
            is UiState.Success -> {
                val totalNodes = uiState.data.data?.search?.edges?.size
                Log.e("HomeScreen", "Success total nodes is: {$totalNodes}")
                ErrorContent(
                    titleError = "Ops error",
                    iconError = Icons.Default.ErrorOutline,
                    onRefresh = { viewModel.getUser() }
                    )
            }
            is UiState.Error -> {
                Log.e("HomeScreen", "Error")
            }
        }
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

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    HomeScreen()
}