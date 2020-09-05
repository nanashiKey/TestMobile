package com.irfan.project.testmobile.models

import java.io.Serializable


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class VideoYoutube (
    var id : String,
    var iso_639_1 : String,
    var iso_3166_1 : String,
    var key : String,
    var name : String,
    var site : String,
    var size : Int,
    var type : String
    ) : Serializable