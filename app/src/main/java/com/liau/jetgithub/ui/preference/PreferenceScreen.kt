package com.liau.jetgithub.ui.preference

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.liau.jetgithub.R
import com.liau.jetgithub.core.di.Injector
import com.liau.jetgithub.core.di.ViewModelFactory
import com.liau.jetgithub.core.model.Setting
import com.liau.jetgithub.ui.component.SettingItem

/**
 * Created by Budiman on 19/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
@Composable
fun PreferenceScreen(
    titlePage: String,
    viewModel: PreferenceViewModel = viewModel(
        factory = ViewModelFactory(Injector.provideRepository(LocalContext.current))
    )
) {
    val darkMode by viewModel.getDarkTheme().observeAsState()
    val languageApp by viewModel.getLanguage().observeAsState()
    Log.e("PreferenceScreen","darkmode is $darkMode and language is : $languageApp")
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titlePage,
            modifier = Modifier.padding(16.dp)
        )
    }

    val settingList = listOf(
        Setting(
            stringResource(id = R.string.title_dark_theme),
            stringResource(id = R.string.subtitle_dark_theme),
            Icons.Default.DarkMode
        ),
        Setting(
            stringResource(id = R.string.title_language),
            stringResource(id = R.string.subtitle_language),
            Icons.Default.Language
        ),
        Setting(
            stringResource(id = R.string.title_about),
            stringResource(id = R.string.subtitle_about),
            Icons.Default.Help
        ),
    )
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = settingList, key = {
            it.title
        }) {
            SettingItem(it)
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
}
