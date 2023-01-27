package com.liau.jetgithub.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.liau.jetgithub.R
import com.liau.jetgithub.core.data.local.entity.User
import com.liau.jetgithub.core.data.network.response.Node
import com.liau.jetgithub.util.Util

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun UserItem(
    nodeItem: Node?,
    navigateToDetail: (User) -> Unit
) {
    val user: User = Util.mappingNodeToUser(nodeItem)
    Card(
        elevation = 0.dp,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navigateToDetail(user) }
                .padding(16.dp)
        ) {
            AsyncImage(
                model = user.avatarUrl,
                contentDescription = stringResource(id = R.string.thumbnail_avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .background(color = Color.LightGray, CircleShape)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                )

                Text(
                    text = user.email,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 16.dp),

                    ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = user.follower.toString(),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground,
                        )

                        Text(
                            text = "Follower",
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = user.following.toString(),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground,
                        )

                        Text(text = "Following")
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = user.repositories.toString(),
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground,
                        )

                        Text(text = "Repository")
                    }
                }
                Text(
                    text = user.company,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

        }
    }

}

