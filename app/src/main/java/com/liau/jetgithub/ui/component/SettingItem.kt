package com.liau.jetgithub.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
            msg = titleSetting.value,
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
    msg: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            title = {
                Text(
                    text = msg,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.fillMaxWidth(1f).padding(8.dp)
                )
            },
            text = {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)) {

                }
            },
            onDismissRequest = onDismiss,
            confirmButton = {},
            dismissButton = {},
        )
    }

}