package com.irfan.project.testmobile.models

import java.io.Serializable

/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class MoviesByGenre (
        var description : String,
        var favorite_count : Int,
        var id : Int,
        var item_count : Int,
        var iso_639_1 : String,
        var list_type : String,
        var name : String,
        var poster_path : String
    ) : Serializable