package com.mitroshenko.newjob.domain.usecases

import android.view.View
import com.mitroshenko.newjob.data.api.model.product.Product
import com.mitroshenko.newjob.databinding.FragmentProductCardBinding

class LoadProductCard {
    private var _binding: FragmentProductCardBinding? = null
    private val binding get() = _binding!!
    fun execute(product: Product) {

        binding.apply {
            tvPrice2.text = product.price.toString()
            tvTitle.text = product.title
            tvDescription.text = product.description
            tvRaiting.text = product.rating.toString()
            tvBrand.text = product.brand
            tvPrice1.text = "Price"
            tvDescriptionTitle.text = "Description"
            tvReviews.text = "Reviews"
        }
    }
}