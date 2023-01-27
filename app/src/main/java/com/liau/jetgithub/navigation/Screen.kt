package com.liau.jetgithub.navigation

/**
 * Created by Budiman on 18/01/2023.
 * Email budiliauw87@gmail.com
 * Github github.com/budiliauw87
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Settings : Screen("settings")
    object DetailUser : Screen("detail")
}