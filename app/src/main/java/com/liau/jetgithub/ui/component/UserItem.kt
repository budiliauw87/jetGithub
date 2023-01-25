package com.liau.jetgithub.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.R

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun UserItem() {
    Card(
        elevation = 0.dp,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(R.drawable.ic_launcher_foreground),
                contentDescription = "Avatar",
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
                    text = "Username",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onBackground,
                )

                Text(
                    text = "demo@example.com",
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onBackground,
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(vertical = 8.dp),

                    ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "63050",
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
                            text = "9",
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
                            text = "175",
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onBackground,
                        )

                        Text(text = "Repository")
                    }
                }

                Text(
                    text = "Huawei Research",
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

        }
    }

}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun UserItemPreview() {
    UserItem()
}