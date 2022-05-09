package com.example.myfavoritefilms

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import com.example.myfavoritefilms.navigation.Screen
import com.example.myfavoritefilms.presentation.screen.splash.SplashViewModel
import com.example.myfavoritefilms.ui.theme.Purple200
import com.example.myfavoritefilms.ui.theme.Purple500
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true){
        delay(2500)
        navController.navigate(Screen.Home.route)
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.cinema),
            contentDescription = "Splash screen image"
        )
    }

}