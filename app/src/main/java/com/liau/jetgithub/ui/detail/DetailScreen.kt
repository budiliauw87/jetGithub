package com.liau.jetgithub.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.core.di.Injector
import com.liau.jetgithub.core.di.ViewModelFactory
import com.liau.jetgithub.ui.component.TabItem
import com.liau.jetgithub.ui.component.UserItem

/**
 * Created by Budiman on 25/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    selectedUser: User?,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository(LocalContext.current))
    ),
) {

    val user = selectedUser
    val totalFollowing = user?.following ?: 0
    val totalFollower = user?.follower ?: 0

    var tabIndex by remember { mutableStateOf(0) }

    viewModel.setPaging(tabIndex+1, user?.login ?: "")
    val lazyPagingItems = viewModel.detailPaging.collectAsLazyPagingItems()
    val refreshing = lazyPagingItems.loadState.refresh is LoadState.Loading

    val tabData = listOf(
        TabItem(title = "Followers", total = totalFollower),
        TabItem(title = "Following", total = totalFollowing),
    )
    //val typeMethod = viewModel.getMethodQuery().collectAsState().value
    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            DetailHeader(user)
        }
        stickyHeader {
            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {
                tabData.forEachIndexed { index, item ->
                    Tab(
                        selected = tabIndex == index,
                        onClick = {
                            tabIndex = index
                            viewModel.setPaging(tabIndex+1, user?.login ?: "")
                        }, text = {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = item.total.toString(),
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(bottom = 5.dp)
                                )
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            }

                        })
                }
            }

        }

        if (!refreshing) {
            itemsIndexed(lazyPagingItems) { _, item ->
                if(item!=null){
                    UserItem(item.node, {  })
                }
            }
        }else{
            item {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
            }
        }
    }
}