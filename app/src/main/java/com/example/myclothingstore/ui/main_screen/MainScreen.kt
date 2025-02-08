package com.example.myclothingstore.ui.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MainScreen(
    navData: MainScreenDataObject,
    onNavigateToLoginScreen: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "uid: ${navData.uid}\n email: ${navData.email}")
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    Firebase.auth.signOut()
                    onNavigateToLoginScreen()
                },
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .size(55.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF83758),
                    contentColor = Color.White,
                )
            ) {
                Text(text = "Log Out", fontSize = 20.sp, fontWeight = FontWeight.W500)
            }
        }
    }
}