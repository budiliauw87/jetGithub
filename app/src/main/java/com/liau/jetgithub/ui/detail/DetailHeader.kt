package com.liau.jetgithub.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.liau.jetgithub.R
import com.liau.jetgithub.core.data.local.entity.User


/**
 * Created by Budiliauw87 on 2023-01-26.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

@Composable
fun DetailHeader(user: User?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = user?.avatarUrl,
            contentDescription = stringResource(id = R.string.thumbnail_avatar),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(120.dp)
                .background(color = Color.LightGray, CircleShape)
                .clip(CircleShape)
        )
        Text(
            text = user?.login ?: "Null",
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = user?.name ?: "Null",
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
                text = user?.location ?: "Null",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(start = 8.dp).widthIn(max=150.dp)

            )

            Icon(
                imageVector = Icons.Filled.Business,
                contentDescription = "Business",
                tint = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Text(
                text = user?.company ?: "Null",
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.widthIn(max=150.dp)
            )
        }
        user?.email?.isNotEmpty().run {

        }
        val email =  user?.email ?: ""
        if(email.isNotEmpty()){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Email",
                    tint = MaterialTheme.colors.surface
                )

                Text(
                    text = email,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.surface,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
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
            val totalRepo = user?.repositories ?: 0
            Text(
                text = "Repositories $totalRepo",
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

