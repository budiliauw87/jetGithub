package com.liau.jetgithub.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.liau.jetgithub.core.model.Setting

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun SettingItem(
    item: Setting
) {
    val showDialog = remember { mutableStateOf(false) }
    val titleSetting = remember { mutableStateOf(item.title) }
    if (showDialog.value) {
        DialogPref(
            titleDialog = titleSetting.value,
            showDialog = showDialog.value,
            onDismiss = { showDialog.value = false }
        )
    }
    Card(
        elevation = 0.dp,
        modifier = Modifier.padding(0.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showDialog.value = true
                }
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = "icontime",
                modifier = Modifier.size(24.dp),
            )

            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.subtitle1,
                )
                Text(
                    text = item.subTitle,
                    style = MaterialTheme.typography.caption,
                )
            }
        }
    }
}

@Composable
fun DialogPref(
    titleDialog: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {

    if (showDialog) {
        AlertDialog(
            text = {
                Column(modifier = Modifier
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
                        verticalAlignment = CenterVertically,
                    ) {
                        RadioButton(
                            selected = true,
                            onClick = { },
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = "Tester",
                            style = MaterialTheme.typography.body2,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = CenterVertically,
                    ) {
                        RadioButton(
                            selected = false,
                            onClick = { },
                            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                        )
                        Text(
                            text = "Tester",
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
    DialogPref(
        "preview",
        true,
        onDismiss ={}
    )
}

