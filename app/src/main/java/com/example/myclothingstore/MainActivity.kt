package com.example.myclothingstore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.myclothingstore.ui.login.SignInScreen
import com.example.myclothingstore.ui.login.SignUpScreen
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.example.myclothingstore.ui.login.data.SignInScreenObject
import com.example.myclothingstore.ui.login.data.SignUpScreenObject
import com.example.myclothingstore.ui.main_screen.MainScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val auth = remember { Firebase.auth }
            val startDestination = auth.currentUser?.let {
                MainScreenDataObject(
                    auth.currentUser!!.uid,
                    auth.currentUser!!.email!!
                )
            } ?: SignInScreenObject

            NavHost(navController = navController, startDestination = startDestination) {
                composable<SignInScreenObject> {
                    SignInScreen(
                        onNavigateToSignUpScreen = {
                            navController.navigate(SignUpScreenObject)
                        },
                        onNavigateToMainScreen = { navigateTo ->
                            navController.navigate(navigateTo)
                        }
                    )
                }
                composable<SignUpScreenObject> {
                    SignUpScreen(
                        onNavigateToMainScreen = { navigateTo ->
                            navController.navigate(navigateTo)
                        },
                        onNavigateToLoginScreen = {
                            navController.navigate(SignInScreenObject)
                        }
                    )
                }
                composable<MainScreenDataObject> { navEntry ->
                    val navData = navEntry.toRoute<MainScreenDataObject>()
                    MainScreen(navData) {
                        navController.navigate(SignInScreenObject)
                    }
                }

            }
        }
    }
}
