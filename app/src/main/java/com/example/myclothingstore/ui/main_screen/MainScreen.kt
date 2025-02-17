package com.example.myclothingstore.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.myclothingstore.R
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.example.myclothingstore.ui.main_screen.bottom_menu.BottomMenu
import com.example.myclothingstore.ui.main_screen.topbar.TopBar
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navData: MainScreenDataObject,
    onNavigateToLoginScreen: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var textState by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color(0xFFF9F9F9),
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopBar(scrollBehavior, onNavigateToLoginScreen) },
        bottomBar = { BottomMenu() },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(20.dp)
        ) {
            item {
                TFSearch(textState)
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "All Featured", fontSize = 24.sp, fontWeight = FontWeight.W600)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        TextButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .padding(horizontal = 10.dp, vertical = 15.dp)
                                .background(Color.White, RoundedCornerShape(8.dp)),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Row {
                                Text(
                                    text = "Sort", color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painterResource(id = R.drawable.ic_sort),
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        }
                        Row {
                            TextButton(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .padding(horizontal = 10.dp)
                                    .background(Color.White, RoundedCornerShape(8.dp)),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "Filter", color = Color.Black
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painterResource(id = R.drawable.ic_filter),
                                    contentDescription = "",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
            item {
                LazyRow(Modifier.background(Color.White, RoundedCornerShape(10.dp))) {
                    items(6) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                model = R.drawable.test_img,
                                contentDescription = "",
                                Modifier
                                    .size(80.dp)
                                    .padding(10.dp),
                            )
                            Text(
                                text = "Text",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W300,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }
                    }
                }
            }
            item {
                ShowBanner(200.dp, R.drawable.test_banner_img)
            }
            item {
                ShowBanner(60.dp, R.drawable.test_banner_img1)
            }
            item {
                LazyRow {
                    items(4) {
                        RowProductIem()
                    }
                }
            }
            item {
                ShowBanner(100.dp, R.drawable.test_banner_img2)
            }
            item {
                ShowBanner(height = 190.dp, R.drawable.test_banner_img3)
            }
            item {
                ShowBanner(80.dp, R.drawable.test_banner_img2)
            }
            item {
                LazyRow {
                    items(4) {
                        RowProductIem()
                    }
                }
            }
            item {
                ShowBanner(400.dp, R.drawable.test_banner_img4)
            }
        }

    }
}

@Composable
private fun ShowBanner(height: Dp, testBannerImg: Int) {
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(height)
            .clip(RoundedCornerShape(10.dp))
    ) {
        AsyncImage(
            model = testBannerImg,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun TFSearch(textState: String) {
    var textState1 = textState
    TextField(
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "",
                tint = Color(0xFFBBBBBB)
            )
        },
        placeholder = ({
            Text(text = "Search any Product..", color = Color(0xFFBBBBBB))
        }),
        value = textState1, onValueChange = { textState1 = it },
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(15.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFFFFFFF),
            unfocusedContainerColor = Color(0xFFFFFFFF),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
    )
}

@Composable
fun Plug(
    padding: PaddingValues,
    navData: MainScreenDataObject,
    onNavigateToLoginScreen: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding), contentAlignment = Alignment.Center
    ) {
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