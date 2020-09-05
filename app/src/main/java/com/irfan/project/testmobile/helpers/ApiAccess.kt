package com.irfan.project.testmobile.helpers

import com.irfan.project.testmobile.models.GenresResponses
import com.irfan.project.testmobile.models.MovieDetailsResponses
import com.irfan.project.testmobile.models.MoviesByGenreResponses
import com.irfan.project.testmobile.models.ReviewsResponses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
interface ApiAccess {
    /**
     * get all movies genre
     * @param api_key
     * @param language
     */
    @GET("genre/movie/list")
    fun getAllMoviesGenre(
        @Query("api_key")  apiKey : String,
        @Query("language") language : String
    ) : Call<GenresResponses>

    /**
     * get all movies genre
     * @param api_key
     * @param language
     */
    @GET("genre/tv/list")
    fun getAllTVsGenre(
        @Query("api_key")  apiKey : String,
        @Query("language") language : String
    ): Call<GenresResponses>

    /**
     * get movie list by genre
     * @param api_key
     * @param language
     * @param page
     */
    @GET("discover/movie")
    fun getMoviesByGenre(
        @Query("api_key") apiKey : String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("with_genres") withGenres : Int
    ) : Call<MoviesByGenreResponses>

    /**
     * get detail of movies selected
     * @param apiKey
     * @param language
     * @param appendToResponse
     */
    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String,
        @Query("language") language : String,
        @Query("append_to_response") appendToResponse : String
    ) : Call<MovieDetailsResponses>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id") movieId :Int ,
        @Query("api_key") apiKey : String,
        @Query("language") language: String,
        @Query("page") page : Int
    ) : Call<ReviewsResponses>
}