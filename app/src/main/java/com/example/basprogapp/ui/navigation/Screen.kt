package com.example.basprogapp.ui.navigation

sealed class Screen(val route:String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailBasprog: Screen("home/{id}") {
        fun createRoute(id: String) = "home/$id"
    }
}
