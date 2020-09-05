package com.irfan.project.testmobile.viewmodels

import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.project.testmobile.helpers.ApiAccess
import com.irfan.project.testmobile.helpers.Const
import com.irfan.project.testmobile.models.Genres
import com.irfan.project.testmobile.models.MovieDetailsResponses
import com.irfan.project.testmobile.models.Review
import com.irfan.project.testmobile.models.ReviewsResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class DetailMBGViewModel : ViewModel() {
    var videoCode : String ?= null
    val apiKey  = Const.apiKey
    val language  = Const.language
    val append_to_response = "videos"
    var movieId : Int ?= null

    var movieDetail : MutableLiveData<MovieDetailsResponses>? = null
    val dataExecuteMovie : LiveData<MovieDetailsResponses>
    get() {
        if(movieDetail == null){
            movieDetail = MutableLiveData()
            getDetailVideo()
        }
        return movieDetail!!
    }

    var reviewsDetail : MutableLiveData<ArrayList<Review>> ?= null
    val dataExecuteReviews : LiveData<ArrayList<Review>>
    get(){
        if(reviewsDetail == null){
            reviewsDetail = MutableLiveData()
            getAllResponses()
        }
        return reviewsDetail!!
    }

    fun getDetailVideo(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.baseMovieUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiAccess = retrofit.create(ApiAccess::class.java)
        apiAccess.getMovieDetail(movieId!!, apiKey, language, append_to_response)
            .enqueue(object : Callback<MovieDetailsResponses>{
                override fun onFailure(call: Call<MovieDetailsResponses>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<MovieDetailsResponses>,
                    response: Response<MovieDetailsResponses>
                ) {
                    if(response.isSuccessful){
                        movieDetail!!.value = response.body()
                    }else{
                        e("TAGERROR", response.message())
                    }
                }

            })
    }

    fun getAllResponses(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.baseMovieUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiAccess = retrofit.create(ApiAccess::class.java)
        apiAccess.getReviews(movieId!!, apiKey, language, page = 1)
            .enqueue(object : Callback<ReviewsResponses>{
                override fun onFailure(call: Call<ReviewsResponses>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ReviewsResponses>,
                    response: Response<ReviewsResponses>
                ) {
                    if(response.isSuccessful){
                        reviewsDetail!!.value = response.body()!!.results
                    }else{
                        e("TAGERROR", response.message())
                    }
                }

            })
    }

}