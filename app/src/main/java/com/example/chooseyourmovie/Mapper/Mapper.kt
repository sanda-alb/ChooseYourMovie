package com.example.chooseyourmovie.Mapper

import com.example.chooseyourmovie.BASE_POSTER_URL
import com.example.chooseyourmovie.models.DatabaseMovie
import com.example.chooseyourmovie.models.Movie
import com.example.chooseyourmovie.models.MovieApiResponse

/**
 * Convert Network results to database objects
 */

fun MovieApiResponse.asDataBaseModel(): List<DatabaseMovie> {
    return results.map {
        DatabaseMovie(
            genreIds = it.genreIds,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            posterLink = BASE_POSTER_URL + it.posterPath
        )
    }
}

/**
 * Map Network results  to domain models
 */


fun MovieApiResponse.asDomainModel() : List<Movie> {
    return results.map {
        Movie(
            genreIds = it.genreIds,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            posterLink = BASE_POSTER_URL + it.posterPath
        )
    }
}

/**
 * Map DatabaseMovies to domain models
 */

fun List<DatabaseMovie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
            genreIds = it.genreIds,
            id = it.id,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            title = it.title,
            voteAverage = it.voteAverage,
            posterLink = it.posterLink
        )
    }
}