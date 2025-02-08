package com.example.myclothingstore.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myclothingstore.R
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SignInScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit = {},
    onNavigateToSignUpScreen: () -> Unit = {},
    onNavigateToForgotPasswordScreen: () -> Unit = {}
) {


    val auth = remember { Firebase.auth }
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal =  32.dp, vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(text = "Welcome Back!", fontSize = 58.sp, fontWeight = FontWeight.W800)
        Spacer(modifier = Modifier.height(30.dp))
        RoundedCornerTextField(
            text = emailState,
            label = "Username or Email",
            R.drawable.ic_email
        ) {
            emailState = it
        }

        Spacer(modifier = Modifier.height(30.dp))

        RoundedCornerTextField(
            text = passwordState,
            label = "Password",
            R.drawable.ic_password_lock
        ) {
            passwordState = it
        }
        if (errorState.isNotEmpty()) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = errorState, color = Color.Red)
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
                emailState,
                passwordState,
                onSignUpSuccess = { navData ->
                    onNavigateToMainScreen(navData)
                    errorState = ""
                },
                onSignUpFailure = { error ->
                    Log.d("MyLog", "Sing In Failure: $error")
                    errorState = error
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