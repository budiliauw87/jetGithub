package com.liau.jetgithub.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.R


/**
 * Created by Budiliauw87 on 2023-01-25.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun ErrorLoadItem(
    errorText: String,
    onClickRefresh: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = errorText,
            style = MaterialTheme.typography.body1,
        )
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary
            ),
            onClick = { onClickRefresh },
        ) {
            Text(
                text = "Refresh",
                color = MaterialTheme.colors.background
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ErrorLoadItemPreview() {
    ErrorLoadItem(
        errorText = stringResource(R.string.load_failed),
        onClickRefresh = {}
    )
}