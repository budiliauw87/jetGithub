package com.liau.jetgithub.ui.setting

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.items
import androidx.paging.compose.collectAsLazyPagingItems
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.ui.component.UserItem

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
) {
    val lazyPagingItems = viewModel.userPaging.collectAsLazyPagingItems()
    var listState = rememberLazyListState()
    val refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, { lazyPagingItems.refresh() })
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = lazyPagingItems,
                key = { item -> item.hashCode() }
            ) { item ->
                item?.let {
                    UserItem(it.node)
                }
            }

            lazyPagingItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        Log.e("HomeScreen", "State refresh")
                    }
                    loadState.append is LoadState.Loading -> { //loading when loadmore
                        Log.e("HomeScreen", "loading item bottom")
                    }
                    loadState.refresh is LoadState.Error -> { // error when refresh
                        val e = lazyPagingItems.loadState.refresh as LoadState.Error
                        Log.e("HomeScreen refresh error", e.error.localizedMessage!!)
                    }
                    loadState.append is LoadState.Error -> { //error when load more
                        val e = lazyPagingItems.loadState.append as LoadState.Error
                        Log.e("HomeScreen append Error : ", e.error.localizedMessage!!)
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