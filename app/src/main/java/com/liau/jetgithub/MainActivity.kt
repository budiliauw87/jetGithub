package com.liau.jetgithub

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liau.jetgithub.navigation.Screen
import com.liau.jetgithub.ui.component.BottomBar
import com.liau.jetgithub.ui.component.SearchBar
import com.liau.jetgithub.ui.preference.FavoriteScreen
import com.liau.jetgithub.ui.preference.HomeScreen
import com.liau.jetgithub.ui.preference.PreferenceScreen
import com.liau.jetgithub.ui.theme.JetGithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { false }
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
    var stateTitle by remember { mutableStateOf("Home") }
    var isSearching by remember { mutableStateOf(false) }
    var querySearch by remember { mutableStateOf("") }

    var context = LocalContext.current as Activity

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stateTitle,
                        color = Color.White,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                actions = {
                    if (stateTitle == "Home") {
                        if (isSearching) {
                            SearchBar(
                                searchText = querySearch,
                                placeholderText = stringResource(R.string.find_by_username),
                                onSearchTextChanged = {
                                    querySearch = it
                                },
                                onClearClick = {
                                    if(!querySearch.isEmpty()) {
                                        querySearch =""
                                    }else{
                                        isSearching = isSearching != true
                                    }

                                },
                                onDone = {
                                    Toast.makeText(context, querySearch, Toast.LENGTH_SHORT).show()
                                }
                            )
                        } else {
                            IconButton(onClick = {
                                isSearching = isSearching != true
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Open Options"
                                )
                            }
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
                stateTitle = stringResource(R.string.menu_home)
                HomeScreen(stateTitle)
            }
            composable(Screen.Favorite.route) {
                stateTitle = stringResource(R.string.menu_favorite)
                FavoriteScreen(stateTitle)
            }
            composable(Screen.Settings.route) {
                stateTitle = stringResource(R.string.menu_settings)
                PreferenceScreen(stateTitle)
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