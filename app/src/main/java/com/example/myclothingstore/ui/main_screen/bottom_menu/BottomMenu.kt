package com.example.myclothingstore.ui.main_screen.bottom_menu

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun BottomMenu() {
    val items = listOf(
        BottomMenuItem.Home,
        BottomMenuItem.Wishlist,
        BottomMenuItem.Cart,
        BottomMenuItem.Search,
        BottomMenuItem.Settings
    )

    var selectedItem by remember { mutableStateOf("Home") }
    NavigationBar(
        containerColor = Color.White,
    ) {
        items.forEach { item ->
            NavigationBarItem(selected = selectedItem == item.title, onClick = {
                selectedItem = item.title
            }, icon = {
                Icon(painter = painterResource(id = item.icon), contentDescription = null)
            },
                label = {
                    Text(text = item.title)
                })
        }
    }
}