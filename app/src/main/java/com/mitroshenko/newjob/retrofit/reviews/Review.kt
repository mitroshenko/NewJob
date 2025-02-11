package com.mitroshenko.newjob.retrofit.reviews

data class ProductReviews(
    val productReviews: List<ProductReviewsModel>
//    val id: Int,
//    val review: ReviewsModel
)

data class ProductReviewsModel(
    val id: Int,
    val rewiews: ReviewsModel
    )

//data class Reviews2(
//    val reviews2: List<re>
//)
data class ReviewsModel(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String
)