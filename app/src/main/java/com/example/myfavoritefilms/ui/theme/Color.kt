package com.example.myfavoritefilms.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val DarkGray = Color(0XFFD8D8D8)
val LightGray = Color(0XFF2A2A2A)


val Colors.welcomeScreenBackgroundColor
@Composable
get() = if(isLight) Color.White else Color.Black


val Colors.ratingStarsColor
@Composable
get() = if(isLight) Color.Yellow else Color.Red


val Colors.textColor
@Composable
get() = if(isLight) Color.Black else Color.White

val Colors.inActivePager
@Composable
get() = if(isLight) DarkGray else LightGray


val Colors.topAppBarContentColor
@Composable
get() = if(isLight) Color.White else LightGray

val Colors.topAppBarBackgroundColor
    @Composable
    get() = if(isLight) Purple500 else Color.Black

