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
fun RowProductIem() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(5.dp)
            .background(Color.White, RoundedCornerShape(10.dp)),
        horizontalAlignment = Alignment.Start,
    ) {
        AsyncImage(
            model = R.drawable.test_row_image,
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
        Text(text = "Women Printed Kurta", fontSize = 16.sp, modifier = Modifier.padding(5.dp))
        Text(
            text = "Neque porro quisquam est qui dolorem ipsum quia",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp)
        )
        Text(
            text = "₹1500",
            fontSize = 16.sp,
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

