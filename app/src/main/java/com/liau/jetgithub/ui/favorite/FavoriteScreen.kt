package com.liau.jetgithub.ui.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun FavoriteScreen(titlePage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text(
            text = titlePage,
            modifier = Modifier.padding(16.dp)
        )
    }
}
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun FavoriteScreenPreview() {
    FavoriteScreen("Favorite")
}
