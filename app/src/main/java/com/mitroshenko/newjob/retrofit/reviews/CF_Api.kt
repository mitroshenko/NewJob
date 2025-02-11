package com.mitroshenko.newjob.retrofit.reviews

import retrofit2.http.GET
import retrofit2.http.Path

interface CF_Api {
    @GET("products/{id}")
    suspend fun getCardById(@Path("id") id: Int): ProductReviews
}