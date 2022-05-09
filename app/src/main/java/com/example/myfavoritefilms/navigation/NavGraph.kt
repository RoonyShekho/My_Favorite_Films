package com.example.myfavoritefilms.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.myfavoritefilms.SplashScreen
import com.example.myfavoritefilms.presentation.screen.Home.HomeScreen
import com.example.myfavoritefilms.presentation.screen.WelcomeScreen
import com.example.myfavoritefilms.presentation.screen.details.DetailsScreen
import com.example.myfavoritefilms.presentation.screen.search.SearchScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoilApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun SetupNavGraph(navController: NavHostController){

    NavHost(navController = navController, startDestination = Screen.Splash.route){

        composable(Screen.Splash.route){
            SplashScreen(navController = navController)
        }

        composable(Screen.Welcome.route){
            WelcomeScreen(navController = navController)
        }

        composable(Screen.Home.route){
            HomeScreen(navController)
        }

        composable(Screen.Details.route, arguments = listOf(navArgument("movieId"){
            type = NavType.IntType
        }
        )
        ){
            DetailsScreen(navController = navController)
        }

        composable(Screen.Search.route){
          SearchScreen(navController)
        }
    }

}