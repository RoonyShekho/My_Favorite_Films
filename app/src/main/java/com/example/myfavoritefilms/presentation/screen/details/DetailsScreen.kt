package com.example.myfavoritefilms.presentation.screen.details

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun DetailsScreen(
    navController:NavHostController,
    viewModel:DetailsViewModel = hiltViewModel()
) {

    val selectedMovie by viewModel.selectedMovie.collectAsState()

    
    DetailsContent(movie = selectedMovie)
}