package com.example.myclothingstore.ui.main_screen.bottom_menu

import com.example.myclothingstore.R

sealed class BottomMenuItem (
    val route: String,
    val title: String,
    val icon: Int
){
    object Home: BottomMenuItem(
        route = "home",
        title = "Home",
        icon = R.drawable.home
    )
    object Wishlist: BottomMenuItem(
        route = "Wishlish",
        title = "Wishlist",
        icon = R.drawable.heart
    )
    object Cart: BottomMenuItem(
        route = "cart",
        title = "Cart",
        icon = R.drawable.cart
    )
    object Search: BottomMenuItem(
        route = "search",
        title = "Search",
        icon = R.drawable.search
    )
    object Settings: BottomMenuItem(
        route = "settings",
        title = "Settings",
        icon = R.drawable.settings
    )

}