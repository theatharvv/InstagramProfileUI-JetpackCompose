package com.urbanmoon.instagramprofile.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.urbanmoon.instagramprofile.ImageWithText
import com.urbanmoon.instagramprofile.R

@Composable
fun ProfileScreen() {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkNight)
    ) {
        Column {
            UsernameBar(username = "username")
            ProfileInformation()
            Buttons()
            Highlights(
                highlights = listOf(
                    ImageWithText("Monday", image = R.drawable.demo),
                    ImageWithText("Tuesday", image = R.drawable.demo),
                    ImageWithText("Wednesday", image = R.drawable.demo),
                    ImageWithText("Thursday", image = R.drawable.demo),
                    ImageWithText("Friday", image = R.drawable.demo)
                )
            )
            PostTabView(
                imageWithTexts = listOf(
                    ImageWithText(text = "posts", image = R.drawable.posts),
                    ImageWithText(text = "reels", image = R.drawable.reel),
                    ImageWithText(text = "tags", image = R.drawable.tag)
                ),
                selectedTabIndex = selectedTabIndex,
                onTabSelected = { selectedTabIndex = it }
            )
            when (selectedTabIndex) {
                0 -> PostSection(
                    posts = listOf(
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo),
                        painterResource(id = R.drawable.demo)
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        BottomMenu(
            items = listOf(
                ImageWithText("Home", R.drawable.home),
                ImageWithText("Search", R.drawable.search),
                ImageWithText("Sleep", R.drawable.add),
                ImageWithText("Likes", R.drawable.heart),
                ImageWithText("Profile", R.drawable.profile)
            ),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BottomMenu(
    items: List<ImageWithText>,
    modifier: Modifier = Modifier,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Gray,
    initialSelectedIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(DarkNight)
            .fillMaxWidth()
            .padding(start = 22.dp, end = 22.dp)
    )
    {
        items.forEachIndexed { index, it ->
            BottomMenuItem(
                item = it,
                isSelected = index == selectedItemIndex,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }

        }
    }
}

@Composable
fun BottomMenuItem(
    item: ImageWithText,
    isSelected: Boolean = false,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = Color.Gray,
    onItemClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onItemClick() }
            .padding(10.dp)
    ) {
        Icon(
            painter = painterResource(id = item.image),
            contentDescription = item.text,
            tint = if (isSelected) activeTextColor else inactiveTextColor,
            modifier = Modifier.size(24.dp)
        )

    }

}

@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier.scale(1.01f)
    ) {
        items(posts.size) { index ->
            Image(
                painter = posts[index],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(1.dp, Color.Gray)
            )
        }
    }
}

@Composable
fun PostTabView(
    imageWithTexts: List<ImageWithText>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = TextWhite,
        modifier = Modifier.padding(top = 10.dp)
    ) {
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = TextWhite,
                unselectedContentColor = Color.Gray,
                onClick = { onTabSelected(index) }
            ) {
                Icon(
                    painter = painterResource(id = item.image),
                    contentDescription = item.text,
                    tint = if (selectedTabIndex == index) TextWhite else Color.Gray,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(24.dp)
                )
            }
        }
    }
}

@Composable
fun Highlights(highlights: List<ImageWithText>) {
    LazyRow(
        modifier = Modifier.padding(start = 2.dp, top = 16.dp)
    ) {
        items(highlights) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                ) {
                    Image(
                        painter = painterResource(id = item.image),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Highlight Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(2.dp)
                            .clip(CircleShape)
                            .clickable { }
                    )
                }
                Text(
                    text = item.text,
                    color = TextWhite,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
fun UsernameBar(username: String) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Back Navigation",
            modifier = Modifier.size(32.dp),
            tint = Color.White
        )
        Spacer(modifier = Modifier.size(20.dp))
        Text(
            text = username,
            color = TextWhite,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            painter = painterResource(id = R.drawable.bluetick),
            contentDescription = "Blue Tick",
            modifier = Modifier
                .padding(top = 2.dp)
                .size(24.dp),
            tint = BlueTick
        )
    }
}

@Composable
fun Buttons() {
    var following by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 18.dp, top = 15.dp, end = 18.dp)
    ) {
        // Follow / Following Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = commonButtonModifier(
                backgroundColor = if (following) Color.Black else LinkBlue
            ).clickable { following = !following }
        ) {
            Text(
                text = if (following) "Following" else "Follow",
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
            )
        }

        // Other Buttons
        SingleButton("Message")
        SingleButton("Email")

        // Navigation Button
        Box(
            contentAlignment = Alignment.Center,
            modifier = commonButtonModifier(backgroundColor = Color.Black)
                .size(34.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.navigation),
                contentDescription = "Navigation",
                tint = TextWhite,
                modifier = Modifier.size(12.dp)
            )
        }
    }
}

@Composable
fun SingleButton(title: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = commonButtonModifier(backgroundColor = Color.Black)
            .clickable { }
    ) {
        Text(
            text = title,
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp)
        )
    }
}

// Common Modifier for buttons with rounded corners, background, and border
fun commonButtonModifier(backgroundColor: Color) = Modifier
    .clip(RoundedCornerShape(7.dp))
    .background(backgroundColor)
    .border(width = 2.dp, color = Color.DarkGray, shape = RoundedCornerShape(6.dp))


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDemo() {
    ProfileScreen()
}
