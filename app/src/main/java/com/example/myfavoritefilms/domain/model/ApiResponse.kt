package com.example.myfavoritefilms.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class ApiResponse(
    val lastUpdated: Long? = null,
    val message: String? = null,
    val movies: List<Movie> = emptyList(),
    val nextPage: Int? = null,
    val prevPage: Int? = null,
    val success: Boolean
)