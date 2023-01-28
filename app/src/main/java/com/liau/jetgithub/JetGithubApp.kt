package com.liau.jetgithub

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.liau.jetgithub.core.model.ConfigApp
import com.liau.jetgithub.navigation.Screen
import com.liau.jetgithub.ui.component.BottomBar
import com.liau.jetgithub.ui.component.SearchBar
import com.liau.jetgithub.ui.detail.DetailScreen
import com.liau.jetgithub.ui.home.HomeScreen
import com.liau.jetgithub.ui.setting.FavoriteScreen
import com.liau.jetgithub.ui.setting.SettingScreen


/**
 * Created by Budiliauw87 on 2023-01-23.
 * budiliauw87.github.io
 * Budiliauw87@gmail.com
 */

@Composable
fun JetGithubApp(viewModel: MainViewModel, configApp: ConfigApp) {
    val navController: NavHostController = rememberNavController()
    var stateTitle by remember { mutableStateOf("Home") }
    var isSearching by remember { mutableStateOf(false) }
    var querySearch by remember { mutableStateOf("") }
    val context = (LocalContext.current) as Activity
    var lastBackPressed: Long by remember { mutableStateOf(0) }
    val selectedUser = viewModel.selectedUser.collectAsState().value
    val isFavorite = viewModel.isFavorite.collectAsState().value

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stateTitle, color = Color.White, modifier = Modifier.fillMaxWidth()
            )
        }, navigationIcon = if (stateTitle == stringResource(R.string.detail_user)) {
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }, actions = {
            if (stateTitle == stringResource(R.string.menu_home)) {
                if (isSearching) {
                    SearchBar(searchText = querySearch,
                        placeholderText = stringResource(R.string.find_by_username),
                        onSearchTextChanged = {
                            querySearch = it
                        },
                        onClearClick = {
                            if (querySearch.isNotEmpty()) {
                                querySearch = ""
                            } else {
                                isSearching = isSearching != true
                            }
                        },
                        onDone = {
                            viewModel.querySearchFlow.value = querySearch
                        })
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
        })
    }, bottomBar = {
        if (stateTitle != stringResource(R.string.detail_user)) BottomBar(navController = navController)
    }, floatingActionButton = {
        if (stateTitle == stringResource(id = R.string.detail_user)) {
            ExtendedFloatingActionButton(onClick = {
                viewModel.setFavorite()
            }, icon = {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Favorite",
                    tint = if (isFavorite) Color.Red else Color.White
                )
            }, text = { Text("Favorite") })
        }
    }

    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                stateTitle = stringResource(R.string.menu_home)
                HomeScreen(viewModel, onBackPressed = {
                    if (isSearching) {
                        isSearching = false
                    } else {
                        val currentTimes = System.currentTimeMillis()
                        if (lastBackPressed + 1000 > currentTimes) {
                            context.finish()
                        } else {
                            lastBackPressed = currentTimes
                            Toast.makeText(
                                context,
                                context.getString(R.string.press_again_exit),
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    }
                }, navigateToDetail = { user ->
                    viewModel.setSelectedUser(user)
                    navController.navigate(Screen.DetailUser.route)
                })
            }
            composable(Screen.Favorite.route) {
                stateTitle = stringResource(R.string.menu_favorite)
                FavoriteScreen(viewModel, navigateToDetail = { user ->
                    viewModel.selectedUser.value = user
                    navController.navigate(Screen.DetailUser.route)
                }, stateFavorite = {
                    viewModel.isFavorite.value = it
                })
            }
            composable(Screen.Settings.route) {
                stateTitle = stringResource(R.string.menu_settings)
                SettingScreen(viewModel, configApp)
            }
            composable(route = Screen.DetailUser.route) {
                stateTitle = stringResource(R.string.detail_user)
                DetailScreen(selectedUser)
            }
        }
    }
}


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetGithubPreview() {

}
