package com.urbanmoon.instagramprofile

import androidx.annotation.DrawableRes

data class ImageWithText(
    val text: String,
    @DrawableRes val image: Int
)
