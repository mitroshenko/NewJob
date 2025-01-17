package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.databinding.RecomRcviewBinding
import com.mitroshenko.newjob.retrofit.Recommendations
import java.text.FieldPosition

class RecommendationAdapter: ListAdapter<Recommendations, RecommendationAdapter.Holder>(Comparator()){
    class Holder(view: View): RecyclerView.ViewHolder(view){
        private val binding = RecomRcviewBinding.bind(view)

        fun bind(recommendations: Recommendations) = with(binding) {
            tvRecTitle.text = recommendations.title
        }
    }
    class Comparator: DiffUtil.ItemCallback<Recommendations>(){
        override fun areItemsTheSame(oldItem: Recommendations, newItem: Recommendations): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Recommendations, newItem: Recommendations): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recom_rcview, parent,false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}