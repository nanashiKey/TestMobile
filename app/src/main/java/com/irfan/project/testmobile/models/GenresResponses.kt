package com.irfan.project.testmobile.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class GenresResponses : Serializable {
    @SerializedName("genres")
    var genres : List<Genres>? = null
}