package com.example.myclothingstore.ui.main_screen.topbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myclothingstore.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehavior: TopAppBarScrollBehavior, onNavigateToLoginScreen: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Transparent
        ),
        title = {
            Icon(
                painter = painterResource(id = R.drawable.splashscreen1),
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier.size(125.dp)
            )
        },
        scrollBehavior = scrollBehavior,
        actions = {
            IconButton(onClick = {
                Firebase.auth.signOut()
                onNavigateToLoginScreen()
            }) {
                Icon(
                    Icons.Default.AccountCircle, contentDescription = "",
                    modifier = Modifier.size(45.dp)
                )
            }
        }
    )

}