package com.irfan.project.testmobile.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.adapters.GenresAdapter
import com.irfan.project.testmobile.adapters.MoviesByGenreAdapters
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.viewmodels.MoviesByGenreViewModels


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 *   class for list all movies by genre
 **/
class MBGActivity : AppCompatActivity(){
    lateinit var moviesByGenreAdapter: MoviesByGenreAdapters
    lateinit var rcView : RecyclerView
    lateinit var methodHelpers: MethodHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mbg_activity)

        val genreId = intent.getIntExtra("genreid", 0)

        rcView = findViewById(R.id.rcView)
        val layoutManager = LinearLayoutManager(this@MBGActivity)
        rcView.setHasFixedSize(true)
        rcView.layoutManager = layoutManager
        rcView.addItemDecoration(DividerItemDecoration(this@MBGActivity, DividerItemDecoration.VERTICAL))
        methodHelpers = MethodHelpers(this@MBGActivity)
        moviesByGenreAdapter = MoviesByGenreAdapters()
        val modelizeData = ViewModelProviders.of(this@MBGActivity).get(MoviesByGenreViewModels::class.java)
        modelizeData.movieId = genreId
        modelizeData.page = 1
        modelizeData.dataExecute.observeForever {
            moviesByGenreAdapter = MoviesByGenreAdapters(this@MBGActivity, it)
            rcView.adapter = moviesByGenreAdapter
        }


    }
}