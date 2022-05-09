package com.example.myfavoritefilms.presentation.screen.search

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.myfavoritefilms.presentation.screen.Home.ListContent


@ExperimentalPagingApi
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController:NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

     val query by searchViewModel.searchQuery
    
    val movies = searchViewModel.searchMovies.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchTopBar(
                text = query,
                onTextChange = { searchViewModel.updateQuery(it) },
                onSearchClick = {
                                searchViewModel.searchMovies(it)
                },
                onCloseClick = { navController.popBackStack() }
            )
        },

        content = {
            ListContent(movies = movies, navController = navController)
        }
    )


}
