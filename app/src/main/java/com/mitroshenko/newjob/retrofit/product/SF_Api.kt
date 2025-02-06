package com.mitroshenko.newjob.retrofit.product

import retrofit2.http.GET
import retrofit2.http.Query

interface SF_Api {
    @GET("products")
    suspend fun getAllProducts(): Products
    @GET("products/search")
    suspend fun getProductsByName(@Query("q") name: String): Products

//    @GET("products/{id}")
//    suspend fun getProductById(): Product
}