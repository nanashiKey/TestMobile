package com.irfan.project.testmobile.activities

import android.os.Bundle
import android.os.Handler
import android.util.Log.e
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.adapters.GenresAdapter
import com.irfan.project.testmobile.adapters.MoviesByGenreAdapters
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.models.MoviesByGenre
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
    var isLoading = true
    var moviesLoaded : ArrayList<MoviesByGenre> ?= null
    var genreId = 0
    lateinit var modelizeData : MoviesByGenreViewModels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mbg_activity)

        genreId = intent.getIntExtra("genreid", 0)
        moviesLoaded = ArrayList<MoviesByGenre>()
        rcView = findViewById(R.id.rcView)
        val layoutManager = LinearLayoutManager(this@MBGActivity)
        rcView.setHasFixedSize(true)
        rcView.layoutManager = layoutManager
        rcView.addItemDecoration(DividerItemDecoration(this@MBGActivity, DividerItemDecoration.VERTICAL))
        methodHelpers = MethodHelpers(this@MBGActivity)
//        moviesByGenreAdapter = MoviesByGenreAdapters()
        modelizeData = ViewModelProviders.of(this@MBGActivity).get(MoviesByGenreViewModels::class.java)
        modelizeData.movieId = genreId
//        modelizeData.page = 1
        getDatas()

        methodHelpers.setWindowsBar(this, R.color.blueDark)
        methodHelpers.setNavBackgroundLuna(this, R.color.blueDark)

        rcView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.itemCount
                val postVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val total = moviesByGenreAdapter.itemCount
                if(dy > 0){
                    if(isLoading){
                        if((visibleItemCount + postVisibleItem) >= total){
                            modelizeData.page += 1
                            e("CHECK", "${modelizeData.page}")
                            getDatas()
                        }
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    fun getDatas(){
        isLoading = false
        modelizeData.dataExecute.observe(this@MBGActivity, Observer {
            for(i in it.indices){
                moviesLoaded!!.add(it.get(i))
                e("TAGERROR", "posisi ke ${i+1} "+moviesLoaded!!.get(i).title)
            }
        })
        Handler().postDelayed({
            if(::moviesByGenreAdapter.isInitialized){
                moviesByGenreAdapter.notifyDataSetChanged()
            }else{
                moviesByGenreAdapter = MoviesByGenreAdapters(this@MBGActivity, moviesLoaded!!)
                rcView.adapter = moviesByGenreAdapter
            }
            isLoading = true
            modelizeData.dataExecute.removeObservers(this@MBGActivity)
        }, 3000)
    }
}