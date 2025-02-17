package com.example.myclothingstore.ui.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.myclothingstore.R
import com.example.myclothingstore.ui.login.data.MainScreenDataObject
import com.example.myclothingstore.ui.main_screen.bottom_menu.BottomMenu
import com.example.myclothingstore.ui.main_screen.topbar.TopBar

@Composable
fun MainScreen(
    navData: MainScreenDataObject,
    onNavigateToLoginScreen: () -> Unit = {}
) {
    val viewModel = viewModel<MainScreenViewModel>()
    MainView(navData, viewModel, onNavigateToLoginScreen)
}

data class RowItem(
    val img: Int,
    val title: String,
    val description: String,
    val price: Int,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    navData: MainScreenDataObject,
    viewModel: MainScreenViewModel = MainScreenViewModel(),
    onNavigateToLoginScreen: () -> Unit = {}
) {
    val categoryList = listOf(
        "Beauty" to R.drawable.category1,
        "Fashion" to R.drawable.category2,
        "Kids" to R.drawable.category3,
        "Mens" to R.drawable.category4,
        "Womens" to R.drawable.category5,
        "Gifts" to R.drawable.category6,
    )
    val RowList = listOf(
        RowItem(R.drawable.row_image1, "Women Printed Kurta", "Neque porro quisquam est qui dolorem ipsum quia", 1500),
        RowItem(R.drawable.row_image2, "HRX by Hrithik Roshan", "Neque porro quisquam est qui dolorem ipsum quia", 2499),
        RowItem(R.drawable.row_image3, "Philips BHH880/10", "Hair Straightening Brush With Keratin Infused Bristles (Black).", 999),
        RowItem(R.drawable.row_image4, "TITAN Men Watch- 1806N", "This Titan watch in Black color is I wanted to buy for a long time", 1500),
    )

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
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
            item { TFSearch(viewModel.textState, viewModel::onTextChange) }
            item { SortAndFilterButtons() }
            item { CategoryItems(categoryList) }
            item { ShowBanner(200.dp, R.drawable.test_banner_img) }
            item { ShowBanner(60.dp, R.drawable.test_banner_img1) }
            item { ProductRow(RowList) }
            item { ShowBanner(100.dp, R.drawable.test_banner_img2) }
            item { ShowBanner(190.dp, R.drawable.test_banner_img3) }
            item { ShowBanner(60.dp, R.drawable.test_banner_img5) }
            item { ProductRow(RowList.shuffled()) }
            item { ShowBanner(400.dp, R.drawable.test_banner_img4) }
        }
    }
}

@Composable
private fun SortAndFilterButtons() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "All Featured", fontSize = 24.sp, fontWeight = FontWeight.W600)
        Row(verticalAlignment = Alignment.CenterVertically) {
            SortButton()
            FilterButton()
        }
    }
}

@Composable
private fun SortButton() {
    TextButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 15.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Text(text = "Sort", color = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painterResource(id = R.drawable.ic_sort),
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun FilterButton() {
    TextButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row {
            Text(text = "Filter", color = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Icon(
                painterResource(id = R.drawable.ic_filter),
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun CategoryItems(categoryList: List<Pair<String, Int>>) {
    LazyRow(Modifier.background(Color.White, RoundedCornerShape(10.dp))) {
        items(categoryList) { (categoryName, imageResId) ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = imageResId,
                    contentDescription = categoryName,
                    modifier = Modifier
                        .size(80.dp)
                        .padding(10.dp),
                )
                Text(
                    text = categoryName,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
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
private fun TFSearch(textState: String, onTextChange: (String) -> Unit) {
    TextField(
        trailingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = null,
                tint = Color(0xFFBBBBBB)
            )
        },
        placeholder = {
            Text(text = "Search any Product..", color = Color(0xFFBBBBBB))
        },
        value = textState,
        onValueChange = onTextChange,
        modifier = Modifier.fillMaxWidth(),
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
private fun ProductRow(RowListFirst: List<RowItem>) {
    LazyRow {
        items(RowListFirst) { data ->
            RowProductIem(data)
        }
    }
}

