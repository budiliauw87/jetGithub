package com.liau.jetgithub.ui.error

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Budiman on 24/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */

@Composable
fun ErrorContent(
    titleError: String,
    iconError: ImageVector,
    onRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            imageVector = iconError,
            contentDescription = "Error icons",
            modifier = Modifier.size(width = 100.dp, height = 100.dp)
        )
        Text(
            text = titleError,
            modifier = Modifier.padding(8.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            onClick = onRefresh,
        ) {
            Text(text = "Refresh", color = MaterialTheme.colors.background)
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ErrorPreview() {
    ErrorContent(
        titleError = "Something wrong",
        iconError = Icons.Default.Home,
        onRefresh = {}
    )
}