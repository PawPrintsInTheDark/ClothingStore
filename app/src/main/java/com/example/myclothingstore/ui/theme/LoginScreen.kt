package com.example.myclothingstore.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.Uri
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun LoginScreen() {

    val auth = Firebase.auth
    Log.d("MyLog", "User email: ${auth.currentUser?.email} ")
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(value = emailState, onValueChange = {
            emailState = it
        })

        Spacer(modifier = Modifier.height(10.dp))

        TextField(value = passwordState, onValueChange = {
            passwordState = it
        })

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            signIn(auth,emailState,passwordState)
        }) {
            Text(text = "Sing In ")
        }
        Button(onClick = {
            signUp(auth,emailState,passwordState)
        }) {
            Text(text = "Sing Up")
        }
        Button(onClick = {
            signOut(auth)
        }) {
            Text(text = "Sing Out")
        }

    }
}

private fun signUp(auth: FirebaseAuth, email: String, password: String){
    if (email.isEmpty() || password.isEmpty()) return
    auth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful){
                Log.d("MyLog", "Sign Up successful!")
                val user = auth.currentUser
            }else{
                Log.d("MyLog", "Sign Up failure!")
            }
        }
}


private fun signIn(auth: FirebaseAuth, email: String, password: String){
    if (email.isEmpty() || password.isEmpty()) return
    auth.signInWithEmailAndPassword(email,password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful){
                Log.d("MyLog", "Sign In successful!")
                val user = auth.currentUser
            }else{
                Log.d("MyLog", "Sign In failure!")
            }
        }
}

private fun signOut(auth: FirebaseAuth){
   auth.signOut()
}



@Preview(showBackground = true)
@Composable
fun Preview(){
    LoginScreen()
}

















