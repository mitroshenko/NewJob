package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.databinding.ReviewsRcviewBinding
import com.mitroshenko.newjob.retrofit.reviews.Review

class ReviewsAdapter: ListAdapter <Review, ReviewsAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ReviewsRcviewBinding.bind(view)
        fun bind(review: Review) = with(binding) {
            tvRaiting2.text = review.title
//            tvRaiting2.text = review.rating.toString()
//            tvComment.text = review.comment
//            tvDate.text = review.date
//            tvName.text = review.reviewerName
        }
        companion object {
            fun create(parent: ViewGroup): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reviews_rcview, parent, false)
                return Holder(view)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<Review>(){
        override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.reviews_rcview, parent,false)
        return Holder(view)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}