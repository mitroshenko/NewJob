package com.mitroshenko.newjob.retrofit

import retrofit2.http.GET
import retrofit2.http.Path

interface SF_Api {
    @GET("offers/{id}")
    suspend fun getProductById(@Path("id") id: Int): Recommendations
    @GET("offers")
    suspend fun getAllRecommendation(): Recommendation
}