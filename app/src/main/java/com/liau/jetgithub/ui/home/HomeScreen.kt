package com.liau.jetgithub.ui.setting

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.state.UiState
import com.liau.jetgithub.ui.component.UserItem
import com.liau.jetgithub.ui.error.ErrorContent
import com.liau.jetgithub.ui.theme.BlueGrey50

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Suppress("FunctionName")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onBackPressed: () -> Unit,
) {

    viewModel.uiStateUser.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                Text(
                    text = "Loading",
                    modifier = Modifier.padding(16.dp)
                )
                viewModel.getUser()
            }
            is UiState.Success -> {
                uiState.data.data?.search?.edges?.let { listEdges ->
                    LazyColumn(
                        Modifier.fillMaxSize()
                            .background(color = BlueGrey50)
                    ) {
                        items(
                            items = listEdges,
                        ) {
                            UserItem()
                        }
                    }
                }
                //Log.e("HomeScreen", "Success total nodes is: {$totalNodes}")


            }
            is UiState.Error -> {
                ErrorContent(
                    titleError = stringResource(R.string.something_error),
                    iconError = Icons.Default.ErrorOutline,
                    onRefresh = { viewModel.getUser() }
                )
            }
        }
    }

    BackHandler {
        onBackPressed()
    }
}