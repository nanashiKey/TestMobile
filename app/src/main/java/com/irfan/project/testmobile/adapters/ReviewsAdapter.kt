package com.irfan.project.testmobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.irfan.project.testmobile.R
import com.irfan.project.testmobile.models.Review


/**
 *   created by Irfan Assidiq
 *   email : assidiq.irfan@gmail.com
 **/
class ReviewsAdapter : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>{
    lateinit var ctx : Context
    lateinit var reviews : ArrayList<Review>
    constructor(){}
    constructor(ctx : Context, reviews : ArrayList<Review>){
        this.ctx = ctx
        this.reviews = reviews
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder {
        val v = LayoutInflater.from(ctx).inflate(R.layout.item_reviews, parent, false)
        return ReviewsViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        val review = reviews.get(position)
        holder.tvAuthor.text = review.author
        holder.tvReview.text = review.content
    }

    class ReviewsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvAuthor : TextView
        var tvReview : TextView
        init {
            tvAuthor = itemView.findViewById(R.id.tvAuthor)
            tvReview = itemView.findViewById(R.id.tvReviews)
        }
    }
}