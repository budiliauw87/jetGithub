package com.liau.jetgithub

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.liau.jetgithub.core.di.ViewModelFactory
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.state.UiState
import com.liau.jetgithub.ui.theme.JetGithubTheme
import com.liau.jetgithub.ui.theme.Purple700
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        var uiState: UiState<ConfigApp> by mutableStateOf(UiState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
            }
        }
        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                is UiState.Loading -> {
                    true
                }
                is UiState.Success -> {
                    false
                }
                is UiState.Error -> {
                    false
                }
                else -> false

            }
        }

        setContent {
            val systemUiController = rememberSystemUiController()
            if (uiState is UiState.Success) {
                val configApp = (uiState as UiState.Success<ConfigApp>).data
                val isDarkTheme = configApp.isDarkMode
                val languageApp = configApp.language
                updateResources(LocalContext.current, languageApp)
                var colorStatus = if (isDarkTheme) {
                    Color.Black
                } else {
                    Purple700
                }
                systemUiController.setSystemBarsColor(
                    color = colorStatus
                )
                // Update the dark content of the system bars to match the theme
                DisposableEffect(systemUiController, isDarkTheme) {
                    systemUiController.systemBarsDarkContentEnabled = false
                    onDispose {}
                }
                JetGithubTheme(darkTheme = isDarkTheme) {
                    JetGithubApp(viewModel, configApp)
                }
            }
        }
    }

    private fun updateResources(context: Context, language: String) {
        context.resources.apply {
            val locale = Locale(language)
            val config = Configuration(configuration)
            context.createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            context.resources.updateConfiguration(config, displayMetrics)
        }
    }
}

