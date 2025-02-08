package com.example.myclothingstore.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornerTextField(
    text: String,
    label: String,
    icon: Int = 0,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!isFocused && text.isEmpty()) {
                    Image(painter = painterResource(id = icon), contentDescription = "", Modifier.size(30.dp))
                    Spacer(modifier = Modifier.width(5.dp))
                }
                Text(text = label, color = Color(0xFF676767))
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF3F3F3),
            unfocusedContainerColor = Color(0xFFF3F3F3),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.onFocusChanged { focusState ->
            isFocused = focusState.isFocused
        }
            .fillMaxWidth()
            .border(1.dp,Color(0xFF626262),RoundedCornerShape(15.dp))
    )
}