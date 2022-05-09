package com.example.myfavoritefilms.presentation.screen.Home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.myfavoritefilms.navigation.Screen
import com.example.myfavoritefilms.presentation.screen.Home.HomeTopBar
import com.example.myfavoritefilms.presentation.screen.Home.HomeViewModel
import com.example.myfavoritefilms.presentation.screen.Home.ListContent




@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
){

    val allMovies = homeViewModel.getAllMovie.collectAsLazyPagingItems()


    
    Scaffold(
        topBar = {
            HomeTopBar(onSearchClicked = {
                navController.navigate(Screen.Search.route)
            })
        },
        content = {
           ListContent(movies = allMovies, navController = navController)
        }

    )
}