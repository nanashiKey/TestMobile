package com.irfan.project.testmobile.helpers

import com.irfan.project.testmobile.models.GenresResponses
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
    @GET("/movie/{movie_id}/lists")
    fun getMoviesByGenre(
        @Query("api_key") apiKey : String,
        @Path("movie_id") movieId : Int,
        @Query("language") language: String,
        @Query("page") page: Int
    )
}