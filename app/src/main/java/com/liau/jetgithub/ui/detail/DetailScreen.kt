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
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Budiman on 25/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(loginId: String) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabData = listOf(
        "Followers",
        "Following",
    )

    LazyColumn(Modifier.fillMaxWidth()) {
        item {
            Text("Header")
        }
        stickyHeader {

            TabRow(
                selectedTabIndex = tabIndex,
                modifier = Modifier.fillMaxWidth().height(80.dp)
            ) {
                tabData.forEachIndexed { index, text ->
                    Tab(selected = tabIndex == index, onClick = {
                        tabIndex = index
                    }, text = {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "99999",
                                style = MaterialTheme.typography.h5,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                                    .padding(bottom = 5.dp)
                            )
                            Text(
                                text = "follower",
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
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
    /*
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier.fillMaxWidth().height(80.dp)
        ) {
            tabData.forEachIndexed { index, text ->
                Tab(selected = tabIndex == index, onClick = {
                    tabIndex = index
                }, text = {
                    Column(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "99999",
                            style = MaterialTheme.typography.h5,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                                .padding(bottom = 5.dp)
                        )
                        Text(
                            text = "follower",
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                })
            }
        }
        Text(
            text = "$loginId $tabIndex",
            modifier = Modifier.padding(16.dp)
        )
    }*/

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailScreenPreview() {
    DetailScreen("Tester working")
}