package com.liau.jetgithub.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.ui.component.ErrorLoadItem
import com.liau.jetgithub.ui.component.UserItem
import com.liau.jetgithub.ui.favorite.EmptyScreen
import com.liau.jetgithub.ui.theme.BlueGrey50
import com.liau.jetgithub.util.Util

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreen(
    viewModel: MainViewModel,
    navigateToDetail: (User) -> Unit,
    stateFavorite: (Boolean)-> Unit,
) {
    val lazyPagingItems = viewModel.favoritePaging.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading
    val pullRefreshState = rememberPullRefreshState(refreshing, {
        lazyPagingItems.refresh()
    })
    stateFavorite(true)
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize()
    ) {
        if (!refreshing) {
            if(lazyPagingItems.itemCount>0){
                LazyColumn(
                    state = listState,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = BlueGrey50)
                ) {

                    itemsIndexed(lazyPagingItems) { _, item ->
                        if (item != null) {
                            val node = Util.mappingUserToNode(item)
                            UserItem(node,navigateToDetail)
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
            }else{
                EmptyScreen()
            }

        }
        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}
