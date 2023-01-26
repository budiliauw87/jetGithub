package com.liau.jetgithub.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.ui.component.ErrorLoadItem
import com.liau.jetgithub.ui.component.UserItem
import com.liau.jetgithub.ui.error.ErrorContent
import com.liau.jetgithub.ui.theme.BlueGrey50

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalMaterialApi::class)
@Suppress("FunctionName")
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onBackPressed: () -> Unit,
    navigateToDetail: (String) -> Unit
) {
    val lazyPagingItems = viewModel.userPaging.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
    val isErrorRefresh = lazyPagingItems.loadState.refresh is LoadState.Error
    val pullRefreshState = rememberPullRefreshState(refreshing, {
        lazyPagingItems.refresh()
    })
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        if (!refreshing) {
            if (isErrorRefresh) {

                ErrorContent(
                    titleError = stringResource(R.string.something_error),
                    iconError = Icons.Default.ErrorOutline,
                    onRefresh = { lazyPagingItems.refresh() }
                )
            } else {
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BlueGrey50)
                ) {

                    itemsIndexed(lazyPagingItems) { _, item ->
                        if (item != null) {
                            UserItem(item.node, navigateToDetail)
                        }
                    }

                    lazyPagingItems.apply {
                        when (loadState.append) {
                            is LoadState.Loading -> { //loading when loadmore
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 16.dp)
                                            .wrapContentWidth(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                            is LoadState.Error -> { //error when load more
                                item {
                                    ErrorLoadItem(
                                        errorText = stringResource(R.string.load_failed),
                                        onClickRefresh = { lazyPagingItems.retry() }
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }

    BackHandler {
        onBackPressed()
    }
}