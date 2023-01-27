package com.liau.jetgithub.ui.favorite

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.R

/**
 * Created by Budiman on 27/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun EmptyScreen(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            imageVector = Icons.Default.ErrorOutline,
            contentDescription = "Error icons",
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        )
        Text(
            text = stringResource(id = R.string.empty_data),
            modifier = Modifier.padding(8.dp)
        )

    }
}