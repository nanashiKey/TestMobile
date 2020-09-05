package com.irfan.project.testmobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.youtube.player.*
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.adapters.ReviewsAdapter
import com.irfan.project.testmobile.helpers.Const
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.viewmodels.DetailMBGViewModel
import kotlinx.android.synthetic.main.detail_movies_activity.*


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 *   class for detail movies by genre activity
 **/
class DetailMBGActivity : AppCompatActivity(), YouTubePlayer.OnInitializedListener{

    val CHECK_REQ = 1
    lateinit var youtubePlay : YouTubePlayerFragment
    lateinit var rcView : RecyclerView
    lateinit var methodHelpers: MethodHelpers
    var youtoubeCue : String ?= null
    lateinit var reviewAdapter : ReviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_movies_activity)
        youtubePlay = fragmentManager.findFragmentById(R.id.youtubePlay) as YouTubePlayerFragment
        rcView = findViewById(R.id.rcView)

        val movieId = intent.getIntExtra("movieId", 0)
        val layoutManager = LinearLayoutManager(this@DetailMBGActivity)
        rcView.setHasFixedSize(true)
        rcView.layoutManager = layoutManager
        rcView.addItemDecoration(DividerItemDecoration(this@DetailMBGActivity, DividerItemDecoration.VERTICAL))
        methodHelpers = MethodHelpers(this@DetailMBGActivity)

        reviewAdapter = ReviewsAdapter()

        val modelize = ViewModelProviders.of(this@DetailMBGActivity).get(DetailMBGViewModel::class.java)
        modelize.movieId = movieId
        modelize.dataExecuteMovie.observeForever{
            tvTitle.text = it.title
            tvOverview.text = it.overview
            tvStatus.text = it.status
            tvTagline.text = it.tagline
            if(it!!.videos!!.results.size > 0){
                youtoubeCue = it.videos!!.results.get(0).key
            }
            youtubePlay.initialize(Const.youtubeAPI, this@DetailMBGActivity)
        }

        modelize.dataExecuteReviews.observeForever {
            reviewAdapter = ReviewsAdapter(this@DetailMBGActivity, it)
            rcView.adapter = reviewAdapter
        }

        methodHelpers.setWindowsBar(this, R.color.blueDark)
        methodHelpers.setNavBackgroundLuna(this, R.color.blueDark)

    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if(!p2){
            if(youtoubeCue != null){
                p1!!.cueVideo(youtoubeCue)
            }
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        if (p1!!.isUserRecoverableError()) {
            p1.getErrorDialog(this, CHECK_REQ).show()
        } else {
                Toast.makeText(this, "ERROR REASON : $p1", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CHECK_REQ){
            youtubePlay.initialize(Const.youtubeAPI, this@DetailMBGActivity)
        }
    }


}