package com.urbanmoon.instagramprofile.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.urbanmoon.instagramprofile.R

// NOTE: The following functions contain hardcoded strings that may need to be updated based on your requirements.
// Please ensure to modify the hardcoded strings inside ProfileInformation(), ProfileStatsRow(), ProfileDescription(), ProfileLinkRow(), and ProfilePicture() functions accordingly.

@Composable
fun ProfileInformation() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        ProfileStatsRow() // Extracted Row as its own function for better organization

        Text(
            "Sam Sulek",
            color = TextWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 18.dp)
        )

        Text(
            "Twitter sam_sulek123\nGo heavy or go home !!",
            color = TextWhite,
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 18.dp)
        )

        ProfileLinkRow()
    }
}

@Composable
fun ProfileStatsRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        ProfilePicture()
        ProfileDescription(value = "15", title = "Posts")
        Spacer(modifier = Modifier.size(14.dp))
        ProfileDescription(value = "5.9M", title = "Followers")
        Spacer(modifier = Modifier.size(14.dp))
        ProfileDescription(value = "150", title = "Following")
        Spacer(modifier = Modifier.size(14.dp))
    }
}

@Composable
fun ProfileLinkRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(18.dp))
        Icon(
            painter = painterResource(id = R.drawable.link),
            contentDescription = "Back Navigation",
            modifier = Modifier.size(14.dp),
            tint = LinkBlue
        )
        Text(
            " https://www.youtube.com/@sam_sulek",
            color = LinkBlue,
            fontSize = 16.sp,
            modifier = Modifier.clickable { /* Handle click */ }
        )
    }
}

@Composable
fun ProfilePicture() {
    Box(
        modifier = Modifier
            .padding(top = 6.dp, start = 16.dp, end = 20.dp, bottom = 10.dp)
            .size(110.dp)
            .clip(CircleShape)
            .background(
                brush = Brush.linearGradient(
                    listOf(Color1, Color2, Color3)

                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.demo),
            contentScale = ContentScale.Crop,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .fillMaxSize()
                .padding(2.5.dp)
                .clip(CircleShape)
                .clickable { /* Handle click */ }
        )
    }
}

@Composable
fun ProfileDescription(value: String, title: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            color = TextWhite,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
        Text(
            text = title,
            color = TextWhite,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}
