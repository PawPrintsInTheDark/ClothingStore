package com.example.myclothingstore.ui.login

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myclothingstore.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen() {

    val auth = remember { Firebase.auth }
    Log.d("MyLog", "User email: ${auth.currentUser?.email} ")
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    var errorState by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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

        Spacer(modifier = Modifier.height(20.dp))
        LoginButton(text = "Sing Up") {
            signUp(
                auth,
                emailState,
                passwordState,
                onSignUpSuccess = {
                    Log.d("MyLog", "Sing Up Success")
                },
                onSignUpFailure = { error ->
                    Log.d("MyLog", "Sing Up Failure: $error")
                }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LoginButton(text = "Sing In") {
            signIn(
                auth,
                emailState,
                passwordState,
                onSignUpSuccess = {
                    Log.d("MyLog", "Sing In Success")
                },
                onSignUpFailure = { error ->
                    Log.d("MyLog", "Sing In Failure: $error")

                },
            )
        }

    }
}

private fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if (email.isEmpty() || password.isEmpty()) {
        onSignUpFailure("Empty email or password")
        return
    }
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignUpSuccess()
            }
        }
        .addOnFailureListener { onSignUpFailure(it.localizedMessage ?: "Sign Up Error") }
}


private fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if (email.isEmpty() || password.isEmpty()) {
        onSignUpFailure("Empty email or password")
        return
    }
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignUpSuccess()
            }
        }
        .addOnFailureListener { onSignUpFailure(it.localizedMessage ?: "Sign In Error") }
}

private fun signOut(auth: FirebaseAuth) {
    auth.signOut()
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    LoginScreen()
}

















