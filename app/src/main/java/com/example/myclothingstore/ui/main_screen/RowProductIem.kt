package com.example.myclothingstore.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myclothingstore.R

@Composable
fun RowProductIem(data: RowItem) {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(5.dp)
            .background(Color.White, RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = data.img,
            contentDescription = "",
            alignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Text(text = data.title, fontSize = 16.sp, modifier = Modifier.padding(5.dp))
        Text(
            text = data.description,
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "₹${data.price}",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

