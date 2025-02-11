package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.databinding.ReviewsRcviewBinding
import com.mitroshenko.newjob.retrofit.product.Product
import com.mitroshenko.newjob.retrofit.product.ReviewsModel


class ReviewsAdapter: ListAdapter <ReviewsModel, ReviewsAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ReviewsRcviewBinding.bind(view)
        fun bind(reviews: ReviewsModel) = with(binding) {
            tvRaiting2.text = reviews.rating.toString()
            tvComment.text = reviews.comment
            tvDate.text = reviews.date
            tvName.text = reviews.reviewerName
        }
        companion object {
            fun create(parent: ViewGroup): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reviews_rcview, parent, false)
                return Holder(view)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<ReviewsModel>(){
        override fun areItemsTheSame(oldItem: ReviewsModel, newItem: ReviewsModel): Boolean {
            return oldItem.date == newItem.date
        }
        override fun areContentsTheSame(oldItem: ReviewsModel, newItem: ReviewsModel): Boolean {
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