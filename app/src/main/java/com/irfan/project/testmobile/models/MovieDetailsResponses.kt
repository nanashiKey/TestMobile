package com.irfan.project.testmobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class MovieDetailsResponses : Serializable {
    @SerializedName("adult")
    var adult : Boolean ?= false

    @SerializedName("backdrop_path")
    var backdropPath : String ?= null

    @SerializedName("belongs_to_collection")
    var belongsToCollection : String ?= null

    @SerializedName("budget")
    var budget : Long ?= null

    @SerializedName("genres")
    var genres : ArrayList<Genres> ?= null

    @SerializedName("homepage")
    var homepage : String ?= null

    @SerializedName("id")
    var id : Int ?= null

    @SerializedName("imdb_id")
    var imdbId : String? = null

    @SerializedName("original_language")
    var originalLanguage : String ?= null

    @SerializedName("original_title")
    var originalTitle : String ?= null

    @SerializedName("overview")
    var overview : String ?= null

    @SerializedName("popularity")
    var popularity : Double ?= null

    @SerializedName("poster_path")
    var posterPath : String ?= null

    @SerializedName("production_companies")
    var productionCompanies : ArrayList<ProductionCompanies> ?= null

    @SerializedName("release_date")
    var releaseDate : String ?= null

    @SerializedName("revenue")
    var revenue : Long ?= null

    @SerializedName("runtime")
    var runtime : Int ?= null

    @SerializedName("spoken_languages")
    var spokenLanguages : ArrayList<SpokenLanguages> ?= null

    @SerializedName("status")
    var status : String ?= null

    @SerializedName("tagline")
    var tagline : String ?= null

    @SerializedName("title")
    var title : String ?= null

    @SerializedName("video")
    var video : Boolean ?= false

    @SerializedName("vote_average")
    var voteAverage : Double ?= null

    @SerializedName("vote_count")
    var voteCount : Long ?= null
}