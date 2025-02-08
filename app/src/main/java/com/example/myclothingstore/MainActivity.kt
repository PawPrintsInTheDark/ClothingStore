package com.example.myclothingstore

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContent {
            val navController = rememberNavController()
            val auth = Firebase.auth
            // проверка, надо ли показывать экран регистрации
            val startDestination = auth.currentUser?.let {
                MainScreenDataObject(
                    auth.currentUser!!.uid,
                    auth.currentUser!!.email!!
                )
            } ?: SignInScreenObject

            NavHost(navController = navController, startDestination = startDestination) {
                composable<SignInScreenObject> {
                    SignInScreen(
                        onNavigateToMainScreen = { navData ->
                            navController.navigate(navData)
                        },
                        onNavigateToSignUpScreen = {
                            navController.navigate(SignUpScreenObject)
                        })
                }
                composable<SignUpScreenObject> {
                    SignUpScreen(
                        onNavigateToMainScreen = { navData ->
                            navController.navigate(navData)
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

// Scaffold(
////                modifier = Modifier.fillMaxSize(),
////                bottomBar = { BottomMenu() },
////            ) {
////            }