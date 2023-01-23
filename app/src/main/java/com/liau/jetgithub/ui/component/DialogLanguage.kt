package com.liau.jetgithub.ui.setting

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.R


/**
 * Created by Budiliauw87 on 2023-01-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */
@Composable
fun DialogLanguage(
    titleDialog: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onValueChange: (String) -> Unit,
    settingValue: String,
) {
    var languageValue by remember { mutableStateOf(settingValue) }
    if (showDialog) {
        AlertDialog(
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = titleDialog,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.fillMaxWidth(1f).padding(vertical = 16.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = languageValue.equals("en"),
                            onClick = { onValueChange("en") },
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = stringResource(R.string.english),
                            style = MaterialTheme.typography.body2,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = languageValue.equals("id"),
                            onClick = { onValueChange("id") },
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = stringResource(R.string.indonesian),
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            },
            onDismissRequest = onDismiss,
            confirmButton = {},
            dismissButton = {},
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DialogPreview() {
    DialogLanguage(
        "preview",
        true,
        onDismiss = {},
        onValueChange = {},
        settingValue ="en"
    )
}