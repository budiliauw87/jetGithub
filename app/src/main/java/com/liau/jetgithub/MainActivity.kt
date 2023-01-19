package com.liau.jetgithub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liau.jetgithub.navigation.Screen
import com.liau.jetgithub.ui.component.BottomBar
import com.liau.jetgithub.ui.preference.FavoriteScreen
import com.liau.jetgithub.ui.preference.HomeScreen
import com.liau.jetgithub.ui.preference.PreferenceScreen
import com.liau.jetgithub.ui.theme.JetGithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetGithubTheme {
                GithubApp()
            }
        }
    }
}

@Composable
fun GithubApp() {
    val navController: NavHostController = rememberNavController()
    val stateTitle = remember { mutableStateOf("Home") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stateTitle.value,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                actions = {
                    if (stateTitle.value == "Home") {
                        IconButton(onClick = {
                        }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Open Options"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                stateTitle.value = "Home"
                HomeScreen(stateTitle.value)
            }
            composable(Screen.Favorite.route) {
                stateTitle.value = "Favorite"
                FavoriteScreen(stateTitle.value)
            }
            composable(Screen.Settings.route) {
                stateTitle.value = "Settings"
                PreferenceScreen(stateTitle.value)
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DefaultPreview() {
    JetGithubTheme {
        GithubApp()
    }
}