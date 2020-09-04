package com.irfan.project.testmobile.viewmodels

import android.util.Log.d
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.project.testmobile.helpers.ApiAccess
import com.irfan.project.testmobile.helpers.Const
import com.irfan.project.testmobile.models.Genres
import com.irfan.project.testmobile.models.MoviesByGenre
import com.irfan.project.testmobile.models.MoviesByGenreResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class MoviesByGenreViewModels : ViewModel(){
    val apiKey  = Const.apiKey
    val language  = Const.language
    var movieId : Int ?= null
    var page : Int ?= null

    var listMoviesByGenre : MutableLiveData<ArrayList<MoviesByGenre>>? = null
    val dataExecute : LiveData<ArrayList<MoviesByGenre>>
        get() {
            if(listMoviesByGenre == null){
                listMoviesByGenre = MutableLiveData()
                loadAllMoviesByGenres()
            }
            return listMoviesByGenre!!
        }

    fun loadAllMoviesByGenres(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.baseMovieUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiAccess = retrofit.create(ApiAccess::class.java)
        val loadMoviesByGenre = apiAccess.getMoviesByGenre(movieId!!,apiKey, language, page!!)
        loadMoviesByGenre.enqueue(object : Callback<MoviesByGenreResponses>{
            override fun onFailure(call: Call<MoviesByGenreResponses>, t: Throwable) {
                e("TAGERROR", t.message!!)
            }

            override fun onResponse(
                call: Call<MoviesByGenreResponses>,
                response: Response<MoviesByGenreResponses>
            ) {
                if(response.isSuccessful){
                    listMoviesByGenre!!.value = response.body()!!.results!!
                    d("TAGCHECK", "total data : ${listMoviesByGenre!!.value!!.size}")
                }else{
                    e("TAGERROR", response.message())
                }
            }

        })
    }
}