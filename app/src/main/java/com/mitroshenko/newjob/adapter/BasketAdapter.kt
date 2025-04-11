package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import com.mitroshenko.newjob.databinding.BasketRcviewBinding


class BasketAdapter(private val onClick: (BasketEntity) -> Unit): ListAdapter<BasketEntity, BasketAdapter.Holder>(Comparator()){
    class Holder(view: View, val onClick: (BasketEntity) -> Unit): RecyclerView.ViewHolder(view){
        private val binding = BasketRcviewBinding.bind(view)
        fun bind(basketEntity: BasketEntity) = with(binding) {
            itemView.setOnClickListener { onClick(basketEntity) }
            tvTitle.text = basketEntity.title
            tvPrice.text = basketEntity.price
            tvBrand.text = basketEntity.brand
            Glide.with(binding.root.context)
                .load(basketEntity.images)
                .into(ivBasket)
        }
        companion object {
            fun create(parent: ViewGroup, onClick: (BasketEntity) -> Unit): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.basket_rcview, parent, false)
                return Holder(view, onClick)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<BasketEntity>(){
        override fun areItemsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: BasketEntity, newItem: BasketEntity): Boolean {
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