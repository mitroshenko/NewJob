package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity
import com.mitroshenko.newjob.databinding.FavouriteRcviewBinding

class FavouriteAdapter(private val onClick: (FavouriteEntity) -> Unit): ListAdapter<FavouriteEntity, FavouriteAdapter.Holder>(Comparator()){
    class Holder(view: View, val onClick: (FavouriteEntity) -> Unit): RecyclerView.ViewHolder(view){
        private val binding = FavouriteRcviewBinding.bind(view)
        fun bind(favouriteEntity: FavouriteEntity) = with(binding) {
            itemView.setOnClickListener { onClick(favouriteEntity) }
            tvTitle.text = favouriteEntity.title
            tvPrice.text = favouriteEntity.price
            tvBrand.text = favouriteEntity.brand
            tvRating.text = favouriteEntity.rating
            Glide.with(binding.root.context)
                .load(favouriteEntity.images)
                .into(ivFavourite)
        }
        companion object {
            fun create(parent: ViewGroup, onClick: (FavouriteEntity) -> Unit): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.favourite_rcview, parent, false)
                return Holder(view, onClick)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<FavouriteEntity>(){
        override fun areItemsTheSame(oldItem: FavouriteEntity, newItem: FavouriteEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: FavouriteEntity, newItem: FavouriteEntity): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.favourite_rcview, parent,false)
        return Holder(view, onClick)
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}