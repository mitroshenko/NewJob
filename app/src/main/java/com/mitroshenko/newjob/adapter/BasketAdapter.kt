package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.data.repository.basket.Entity
import com.mitroshenko.newjob.databinding.BasketRcviewBinding


class BasketAdapter(private val onClick: (Entity) -> Unit): ListAdapter<Entity, BasketAdapter.Holder>(Comparator()){
    class Holder(view: View, val onClick: (Entity) -> Unit): RecyclerView.ViewHolder(view){
        private val binding = BasketRcviewBinding.bind(view)
        fun bind(entity: Entity) = with(binding) {
            itemView.setOnClickListener { onClick(entity) }
            tvTitle.text = entity.title
            tvPrice.text = entity.price
            tvBrand.text = entity.brand
            tvRating.text = entity.rating
            Glide.with(binding.root.context)
                .load(entity.images)
                .into(ivFavourite)
        }
        companion object {
            fun create(parent: ViewGroup, onClick: (Entity) -> Unit): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.basket_rcview, parent, false)
                return Holder(view, onClick)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<Entity>(){
        override fun areItemsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Entity, newItem: Entity): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.basket_rcview, parent,false)
        return Holder(view, onClick)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}