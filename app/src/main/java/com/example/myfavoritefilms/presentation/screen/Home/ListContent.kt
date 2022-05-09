package com.example.myfavoritefilms.presentation.screen.Home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.myfavoritefilms.util.Constants.BASE_URL
import com.example.myfavoritefilms.domain.model.Movie
import com.example.myfavoritefilms.navigation.Screen
import com.example.myfavoritefilms.ui.theme.Shapes
import com.example.myfavoritefilms.R

@ExperimentalCoilApi
@Composable
fun ListContent(
    movies: LazyPagingItems<Movie>,
    navController:NavHostController
){

    val result = handlePagingResult(heroes = movies)

    if(result){

        LazyColumn(
            contentPadding = PaddingValues(
                all = 8.dp,
            ),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            items(
                items = movies,
                key = {movie->
                    movie.id
                }
            ){movie->
                movie?.let {
                    MovieItem(movie = it, navController = navController)
                }

            }
        }
    }

}

@Composable
fun handlePagingResult(
    heroes:LazyPagingItems<Movie>
):Boolean{
    heroes.apply{
        val error = when{
            loadState.refresh is LoadState.Error-> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error-> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error-> loadState.append as LoadState.Error
            else -> null
        }

        return when{
            loadState.refresh is LoadState.Loading->{
                false
            }

            error!=null->{
                Log.d("ViewModel", error.error.message!!)
               // EmptyScreen(error = error)
                false
            }

            else-> true

        }
    }
}


@ExperimentalCoilApi
@Composable
fun MovieItem(
    movie: Movie,
    navController:NavHostController
){

    
    val painter = rememberImagePainter(data = "$BASE_URL${movie.image}"){
        placeholder(R.drawable.ic_launcher_background)
    }
    
    Box(modifier = Modifier
        .height(400.dp)
        .clickable {
            navController.navigate(Screen.Details.getFilmId(movie.id))
        },
        contentAlignment = Alignment.BottomStart
    )
    {
        
        Surface(shape = RoundedCornerShape(size = 20.dp)) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painter,
                contentDescription = "Movie image",
                contentScale = ContentScale.Crop
            )
        }

       Surface(
           modifier = Modifier
               .fillMaxHeight(0.3f)
               .fillMaxWidth(),
           color = Color.Black.copy(alpha = ContentAlpha.medium),
           shape = RoundedCornerShape(
               bottomStart = 20.dp,
               bottomEnd = 20.dp
           ),
       ) {
           Column(modifier = Modifier
               .fillMaxSize()
               .padding(all = 20.dp)) {

               Text(
                   text = movie.title,
                   fontWeight = FontWeight.Bold,
                   fontSize = MaterialTheme.typography.h4.fontSize,
                   maxLines = 1,
                   color = Color.White
               )

               Text(
                   text = movie.plot,
                   fontSize = MaterialTheme.typography.subtitle1.fontSize,
                   maxLines = 4,
                   overflow = TextOverflow.Ellipsis,
                   color = Color.White
               )
           }

        }
    }
}