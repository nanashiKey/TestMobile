package com.irfan.project.testmobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class MoviesByGenreResponses : Serializable{
    @SerializedName("page")
    var page : Int? = null

    @SerializedName("total_results")
    var total_results : Int ?= null

    @SerializedName("total_pages")
    var total_pages : Int ?= null

    @SerializedName("results")
    var results : ArrayList<MoviesByGenre>? = null
}
