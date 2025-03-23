package com.mitroshenko.newjob.data.model.product



data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val rating: Float,
    val brand: String,
    val discountPercentage: Float,
    val description: String,
    val reviews: List<Review>,
    val images: List<String>
)

data class Review(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String
)


