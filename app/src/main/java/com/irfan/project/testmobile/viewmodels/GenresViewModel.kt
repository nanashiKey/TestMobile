package com.irfan.project.testmobile.viewmodels

import android.util.Log.d
import android.util.Log.e
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfan.project.testmobile.helpers.ApiAccess
import com.irfan.project.testmobile.helpers.Const
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.models.Genres
import com.irfan.project.testmobile.models.GenresResponses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class GenresViewModel : ViewModel() {
    val apiKey  = Const.apiKey
    val language  = Const.language

    var listGenres : MutableLiveData<List<Genres>>? = null
    val dataExecute : LiveData<List<Genres>>
        get() {
         if(listGenres == null){
             listGenres = MutableLiveData()
             loadAllMoviesGenres()
         }
            return listGenres!!
        }

    fun loadAllMoviesGenres(){
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.baseMovieUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiAccess = retrofit.create(ApiAccess::class.java)
        val loadGenres = apiAccess.getAllMoviesGenre(apiKey, language)
        loadGenres.enqueue(object : Callback<GenresResponses>{
            override fun onFailure(call: Call<GenresResponses>, t: Throwable) {
                e("TAGERROR", t.message!! )
            }

            override fun onResponse(
                call: Call<GenresResponses>,
                response: Response<GenresResponses>
            ) {
                if(response.isSuccessful){
                    listGenres!!.value = response.body()!!.genres!!
                    d("TAGCHECK", "${listGenres!!.value!!.size}")
                }else{
                    e("TAGERROR", response.message())
                }
            }

        })
    }

}