package com.example.myclothingstore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myclothingstore.data.SplashData
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            SplashScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreen() {
    val splashData = listOf(
        SplashData(
            R.drawable.splash_screen1,
            "Choose Products",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
        ),
        SplashData(
            R.drawable.splash_screen2,
            "Make Payment",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
        ),
        SplashData(
            R.drawable.splash_screen3,
            "Get Your Order",
            "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit."
        ),
    )
    val pagerState = rememberPagerState { splashData.size }
    val scope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState, key = { splashData[it] },
        ) { index ->
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                ) {
                    val annotatedString = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Black)) {
                            append("${index + 1}/")
                        }
                        withStyle(style = SpanStyle(color = Color(0xFFA0A0A1))) {
                            append("${pagerState.pageCount}")
                        }
                    }
                    Text(
                        text = annotatedString,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W600
                    )
                    Text(text = "Skip", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(350.dp), contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = splashData[index].image),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier.size(350.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            text = "Choose Products",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.W900
                        )
                        Text(
                            text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit.",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFA8A8A9)
                        )
                    }

                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                        .fillMaxWidth()
                        .padding(25.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                pagerState.currentPage - 1
                            )
                        }
                    }) {
                        Text(
                            text = if (index != 0) "Prev" else "",
                            fontSize = 20.sp,
                            color = Color(0xFFC4C4C4),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    DotsIndicator(pagerState.currentPage, pagerState.pageCount)
                    TextButton(onClick = {
                        scope.launch {
                            if (index != splashData.size - 1)
                                pagerState.animateScrollToPage(
                                    pagerState.currentPage + 1
                                ) else {
                                //TODO enter to login screen
                            }
                        }
                    }) {
                        Text(
                            text = if (index == splashData.size - 1) "Get Started" else "Next",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF83758)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun DotsIndicator(currentPage: Int, pageCount: Int) {
    Row(
        modifier = Modifier
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (index in 0 until pageCount) {
            val dotSize = if (index == currentPage) {
                Modifier.size(width = 50.dp, height = 18.dp)
            } else {
                Modifier.size(18.dp)
            }
            val dotColor = if (index == currentPage) Color(0xFF17223B) else Color(0x99C4C4C4)

            Box(
                modifier = dotSize
                    .padding(5.dp)

                    .background(dotColor, shape = androidx.compose.foundation.shape.CircleShape)
            )
        }
    }
}