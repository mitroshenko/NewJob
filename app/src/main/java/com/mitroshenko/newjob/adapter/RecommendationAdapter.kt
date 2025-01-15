package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.databinding.RecomRcviewBinding

class RecommendationAdapter: RecyclerView.Adapter<RecommendationAdapter.RecommendationHolder>() {

    val recList = ArrayList<Recommendations>()


    class RecommendationHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = RecomRcviewBinding.bind(item)
        fun bind(recom: Recommendations) = with(binding){
            ivRec.setImageResource(recom.imageId)
            tvRecTitle.text = recom.title

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recom_rcview, parent, false)
        return RecommendationHolder(view)
    }

    override fun getItemCount(): Int {
        return recList.size
    }

    override fun onBindViewHolder(holder: RecommendationHolder, position: Int) {
        holder.bind(recList[position])
    }

    fun addRecommendations(recommendations: Recommendations){
        recList.add(recommendations)
        notifyDataSetChanged()
    }
}