package com.liau.jetgithub.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.liau.jetgithub.R


/**
 * Created by Budiliauw87 on 2023-01-26.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

@Composable
fun DetailHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "",
            contentDescription = stringResource(id = R.string.thumbnail_avatar),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .background(color = Color.LightGray, CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = "Tester",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = "subtitle",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.surface,
        )
        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "location",
                tint = MaterialTheme.colors.surface
            )

            Text(
                text = "City name",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(start = 8.dp)

            )

            Icon(
                imageVector = Icons.Filled.Business,
                contentDescription = "Business",
                tint = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = "Building name",
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.surface,
            )
        }

        Row (verticalAlignment = Alignment.CenterVertically){
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email",
                tint = MaterialTheme.colors.surface
            )

            Text(
                text = "JhonDoe@example.com",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.ContentPaste,
                contentDescription = "Repositories",
                tint = MaterialTheme.colors.surface
            )

            Text(
                text = "Repositori 888999",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailHeaderPreview() {
    DetailHeader()
}