package com.example.myclothingstore.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myclothingstore.R
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.example.myclothingstore.ui.login.state.LoginScreenEvent
import com.example.myclothingstore.ui.login.state.LoginScreenState
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit = {},
    onNavigateToLoginScreen: () -> Unit = {},
) {
    val viewModel = viewModel<SignInViewModel>()
    SignUpView(
        onNavigateToMainScreen,
        onNavigateToLoginScreen,
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SignUpView(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit = {},
    onNavigateToLoginScreen: () -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit = {}
) {
    val auth = remember { Firebase.auth }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal =  32.dp, vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(text = "Create an account", fontSize = 58.sp, fontWeight = FontWeight.W800)
        Spacer(modifier = Modifier.height(30.dp))
        RoundedCornerTextField(
            text = state.email,
            label = "Username or Email",
            R.drawable.ic_email
        ) {
            onEvent(LoginScreenEvent.EmailChanged(it))
        }

        Spacer(modifier = Modifier.height(30.dp))

        RoundedCornerTextField(
            text = state.password,
            label = "Password",
            R.drawable.ic_password_lock
        ) {
            onEvent(LoginScreenEvent.PasswordChanged(it))
        }
        Spacer(modifier = Modifier.height(30.dp))
        RoundedCornerTextField(
            text = state.confirmPassword,
            label = "ConfirmPassword",
            R.drawable.ic_password_lock
        ) {
            onEvent(LoginScreenEvent.ConfirmPasswordChanged(it))
        }
        if (state.error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = state.error, color = Color.Red)
        }

        Spacer(modifier = Modifier.height(30.dp))
        LoginButton(text = "Sing Up") {
            if (state.password != state.confirmPassword) {
                onEvent(LoginScreenEvent.EmailChanged("Passwords do not match"))
                return@LoginButton
            }
            signUp(
                auth,
                state.email,
                state.password,
                onSignUpSuccess = { navData ->
                    onNavigateToMainScreen(navData)
                    onEvent(LoginScreenEvent.EmailChanged(""))
                },
                onSignUpFailure = { error ->
                    Log.d("MyLog", "Sing Up Failure: $error")
                    onEvent(LoginScreenEvent.EmailChanged(error))
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ShowAuthNavigationText("I Already Have an Account ", "Login") {
            onNavigateToLoginScreen()
        }
    }
}

private fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: (MainScreenDataObject) -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if (email.isEmpty() || password.isEmpty()) {
        onSignUpFailure("Empty email or password")
        return
    }
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignUpSuccess(
                    MainScreenDataObject(
                        uid = task.result.user?.uid!!,
                        email = task.result.user?.email!!
                    )
                )
            }
        }
        .addOnFailureListener { onSignUpFailure(it.localizedMessage ?: "Sign Up Error") }
}