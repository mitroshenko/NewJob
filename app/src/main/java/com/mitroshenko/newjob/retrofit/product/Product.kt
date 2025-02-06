package com.mitroshenko.newjob.retrofit.product

data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val rating: Float,
    val brand: String,
    val discountPercentage: Float
)
