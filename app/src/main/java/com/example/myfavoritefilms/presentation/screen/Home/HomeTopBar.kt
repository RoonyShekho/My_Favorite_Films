package com.example.myfavoritefilms.presentation.screen.Home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import com.example.myfavoritefilms.ui.theme.topAppBarBackgroundColor
import com.example.myfavoritefilms.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    onSearchClicked:()->Unit
){
   
    TopAppBar(
        title = {
            Text(text = "Explore",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
            }
        },

        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    ) 
   
}