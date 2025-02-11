package com.mitroshenko.newjob.retrofit.product



data class Product(
    val id: Int,
    val title: String,
    val price: Float,
    val rating: Float,
    val brand: String,
    val discountPercentage: Float,
    val description: String,
    val reviews: ReviewsModel
)

data class ReviewsModel(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String
)


