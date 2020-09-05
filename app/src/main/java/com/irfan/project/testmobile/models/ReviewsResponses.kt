package com.irfan.project.testmobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class ReviewsResponses :Serializable {
    @SerializedName("id")
    var id : Int ?= null

    @SerializedName("page")
    var page : Int ?= null

    @SerializedName("results")
    var results : ArrayList<Review> ? = null

    @SerializedName("total_pages")
    var totalPages : Int ?= null

    @SerializedName("total_results")
    var totalResults : Int ?= null
}