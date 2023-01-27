package com.liau.jetgithub.ui.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.MainViewModel
import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.ui.component.TabItem

/**
 * Created by Budiman on 25/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    viewModel: MainViewModel,
    selectedUser: User?
) {

    //val user = viewModel.selectedUser.collectAsState().value
    val user = selectedUser
    val totalFollowing = user?.following ?: 0
    val totalFollower = user?.follower ?: 0

    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        TabItem(title = "Followers", total = totalFollower),
        TabItem(title = "Following", total = totalFollowing),
    )
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
        items(100) {
            Text(
                it.toString(),
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}