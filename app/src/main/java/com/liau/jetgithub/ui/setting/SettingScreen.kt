package com.liau.jetgithub.ui.setting

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.core.di.Injector
import com.liau.jetgithub.core.di.ViewModelFactory
import com.liau.jetgithub.core.model.ConfigApp

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun SettingScreen(
    configApp: ConfigApp,
    viewModel: SettingViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository(LocalContext.current))
    )
) {
    var showDialogLanguage by remember { mutableStateOf(false) }
    var isDarkMode by remember { mutableStateOf(configApp.isDarkMode) }
    var languageValue by remember { mutableStateOf(configApp.language) }
    val titleDialogLanguage = stringResource(id = R.string.title_language)
    if (showDialogLanguage) {
        DialogLanguage(
            titleDialog = titleDialogLanguage,
            showDialog = showDialogLanguage,
            onDismiss = { showDialogLanguage = false },
            onValueChange = {
                languageValue = it
                viewModel.saveLanguage(it)
                showDialogLanguage = false
            },
            settingValue = languageValue
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        // dark mode setting
        Card(
            elevation = 0.dp,
            modifier = Modifier.padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DarkMode,
                    contentDescription = "darkmode icon",
                    modifier = Modifier.size(24.dp),
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.title_dark_theme),
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Text(
                        text = stringResource(id = R.string.subtitle_dark_theme),
                        style = MaterialTheme.typography.caption,
                    )
                }
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = {
                        isDarkMode = it
                        viewModel.saveDarkMode(isDarkMode)
                    }
                )
            }
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
        // language setting
        Card(
            elevation = 0.dp,
            modifier = Modifier.padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDialogLanguage = true
                    }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Language,
                    contentDescription = "language icon",
                    modifier = Modifier.size(24.dp),
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = titleDialogLanguage,
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Text(
                        text = stringResource(id = R.string.subtitle_language),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
        // About setting
        Card(
            elevation = 0.dp,
            modifier = Modifier.padding(0.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Help,
                    contentDescription = "language icon",
                    modifier = Modifier.size(24.dp),
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.title_about),
                        style = MaterialTheme.typography.subtitle1,
                    )
                    Text(
                        text = stringResource(id = R.string.subtitle_about),
                        style = MaterialTheme.typography.caption,
                    )
                }
            }
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun SettingPreview() {
    SettingScreen(ConfigApp("en", true))
}
