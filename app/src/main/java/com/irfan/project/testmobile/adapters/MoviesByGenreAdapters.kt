package com.irfan.project.testmobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.helpers.Const
import com.irfan.project.testmobile.helpers.MethodHelpers
import com.irfan.project.testmobile.models.MoviesByGenre


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class MoviesByGenreAdapters : RecyclerView.Adapter<MoviesByGenreAdapters.MoviesByGenresViewHolder>{
    lateinit var ctx : Context
    lateinit var genreMovies : ArrayList<MoviesByGenre>
    private lateinit var methodHelpers: MethodHelpers
    constructor(){}
    constructor(ctx : Context, genreMovies : ArrayList<MoviesByGenre>){
        this.ctx = ctx
        this.genreMovies = genreMovies
        methodHelpers = MethodHelpers(ctx)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesByGenresViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_movies_genre, parent, false)
        return MoviesByGenresViewHolder(v)
    }

    override fun getItemCount(): Int {
        return genreMovies.size
    }

    override fun onBindViewHolder(holder: MoviesByGenresViewHolder, position: Int) {
        val genreMovie = genreMovies.get(position)
        holder.tvTitle.text = genreMovie.name
        holder.tvDesc.text = genreMovie.description
        Glide.with(ctx)
            .load(Const.baseUrlPosterPath+Const.imageSize+genreMovie.poster_path)
            .error(R.drawable.ic_film)
            .placeholder(R.drawable.ic_film)
            .into(holder.imgPoster)
        holder.llayout.setOnClickListener {
            methodHelpers.showShortToast("genre id = ${genreMovie.id} dan genre name = ${genreMovie.name}")
        }
    }

    class MoviesByGenresViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var llayout : LinearLayout
        var imgPoster : ImageView
        var tvTitle : TextView
        var tvDesc : TextView
        init {
            llayout = itemView.findViewById(R.id.llayout)
            imgPoster = itemView.findViewById(R.id.imgPoster)
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvDesc = itemView.findViewById(R.id.tvDesc)
        }
    }
}