package com.example.myclothingstore.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
fun SignInScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit = {},
    onNavigateToSignUpScreen: () -> Unit = {},
    onNavigateToForgotPasswordScreen: () -> Unit = {},
) {
    val viewModel = viewModel<SignInViewModel>()
    SignInView(
        onNavigateToMainScreen,
        onNavigateToSignUpScreen,
        onNavigateToForgotPasswordScreen,
        state = viewModel.state,
        onEvent = viewModel::onEvent
    )
}


@Composable
fun SignInView(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit = {},
    onNavigateToSignUpScreen: () -> Unit = {},
    onNavigateToForgotPasswordScreen: () -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit = {}
) {
    val auth = remember { Firebase.auth }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(text = "Welcome Back!", fontSize = 58.sp, fontWeight = FontWeight.W800)
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
        if (state.error.isNotEmpty()) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = state.error, color = Color.Red)
        }

        TextButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { onNavigateToForgotPasswordScreen() }) {
            Text(text = "Forgot Password?", color = Color(0xFFF83758), fontWeight = FontWeight.W400)
        }

        Spacer(modifier = Modifier.height(20.dp))

        LoginButton(text = "Login") {
            signIn(
                auth,
                state.email,
                state.password,
                onSignUpSuccess = { navData ->
                    onNavigateToMainScreen(navData)
                    onEvent(LoginScreenEvent.EmailChanged(""))
                },
                onSignUpFailure = { error ->
                    Log.d("MyLog", "Sing In Failure: $error")
                    onEvent(LoginScreenEvent.EmailChanged(error))
                },
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        ShowAuthNavigationText("Create An Account ", "Sign Up") {
            onNavigateToSignUpScreen()
        }

    }
}

private fun signIn(
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
    auth.signInWithEmailAndPassword(email, password)
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
        .addOnFailureListener { onSignUpFailure(it.localizedMessage ?: "Sign In Error") }
}