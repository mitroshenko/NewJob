package com.mitroshenko.newjob.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mitroshenko.newjob.R
import com.mitroshenko.newjob.data.model.product.Product
import com.mitroshenko.newjob.databinding.BasketRcviewBinding


class BasketAdapter(private val onClick: (Product) -> Unit): ListAdapter<Product, BasketAdapter.Holder>(Comparator()){
    class Holder(view: View, val onClick: (Product) -> Unit): RecyclerView.ViewHolder(view){
        private val binding = BasketRcviewBinding.bind(view)
        fun bind(products: Product) = with(binding) {
            itemView.setOnClickListener { onClick(products) }
            tvTitle.text = products.title
            tvPrice.text = products.price.toString()
            tvBrand.text = products.brand
            tvRating.text = products.rating.toString()
//                Glide.with()
//                    .load(products.images[0])
//                    .into(imFoto)
        }
        companion object {
            fun create(parent: ViewGroup, onClick: (Product) -> Unit): Holder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.basket_rcview, parent, false)
                return Holder(view, onClick)
            }
        }
    }
    class Comparator: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
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