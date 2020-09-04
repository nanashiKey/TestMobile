package com.irfan.project.testmobile.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.activities.MBGActivity
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.models.Genres
import com.irfan.project.testmobile.models.GenresResponses


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class GenresAdapter : RecyclerView.Adapter<GenresAdapter.GenresViewHolder> {
    private lateinit var ctx : Context
    lateinit var genres : List<Genres>
    private lateinit var methodHelpers: MethodHelpers
    constructor(){}
    constructor(ctx : Context, genres : List<Genres>){
        this.ctx = ctx
        this.genres = genres
        methodHelpers = MethodHelpers(ctx)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_genres, parent, false)
        return GenresViewHolder(v)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre = genres.get(position)
        holder.tvTitle.text = genre.name
        holder.llayout.setOnClickListener {
            methodHelpers.showShortToast("genre id = ${genre.id} dan genre name = ${genre.name}")
            val intent = Intent(ctx, MBGActivity::class.java)
            intent.putExtra("genreid", genre.id)
            ctx.startActivity(intent)
        }
    }

    class GenresViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var llayout : LinearLayout
        var imgPoster : ImageView
        var tvTitle : TextView
        init {
            llayout = itemView.findViewById(R.id.llayout)
            imgPoster = itemView.findViewById(R.id.imgPoster)
            tvTitle = itemView.findViewById(R.id.tvTitle)
        }
    }
}