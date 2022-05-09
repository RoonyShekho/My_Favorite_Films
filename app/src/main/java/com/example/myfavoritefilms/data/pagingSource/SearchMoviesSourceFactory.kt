package com.example.myfavoritefilms.data.pagingSource


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myfavoritefilms.data.remote.MovieApi
import com.example.myfavoritefilms.domain.model.Movie
import javax.inject.Inject

class SearchMoviesSourceFactory @Inject constructor(
    private val movieApi: MovieApi,
    private val query:String

):PagingSource<Int, Movie>() {



    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try{
            val apiResponse = movieApi.searchMovies(query)
            val movies = apiResponse.movies

            if(movies.isNotEmpty()){
                LoadResult.Page(
                    data = movies,
                    prevKey = apiResponse.prevPage,
                    nextKey = apiResponse.nextPage
                )
            }else{
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}
