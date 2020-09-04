package com.irfan.project.testmobile.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.adapters.GenresAdapter
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.viewmodels.GenresViewModel

class MainActivity : AppCompatActivity() {

    lateinit var genresAdapter: GenresAdapter
    lateinit var rcView : RecyclerView
    lateinit var methodHelpers: MethodHelpers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcView = findViewById(R.id.rcView)
        val layoutManager = GridLayoutManager(this@MainActivity, 3)
        rcView.setHasFixedSize(false)
        rcView.layoutManager = layoutManager
        methodHelpers = MethodHelpers(this@MainActivity)

        val modelizeData = ViewModelProviders.of(this@MainActivity).get(GenresViewModel::class.java)
        modelizeData.dataExecute.observeForever {
            genresAdapter = GenresAdapter(this@MainActivity, it)
            rcView.adapter = genresAdapter
            methodHelpers.showLongToast("cek " +genresAdapter.genres.size)
        }

    }
}
