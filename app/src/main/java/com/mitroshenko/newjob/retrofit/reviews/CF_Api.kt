package com.mitroshenko.newjob.retrofit.reviews

import com.mitroshenko.newjob.retrofit.product.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface CF_Api {
    @GET("products")
    suspend fun getCardById(): Reviews
}