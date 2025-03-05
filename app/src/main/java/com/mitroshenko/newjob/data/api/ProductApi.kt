package com.mitroshenko.newjob.data.api

import com.mitroshenko.newjob.data.api.model.product.Product
import com.mitroshenko.newjob.data.api.model.product.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products")
    suspend fun getAllProducts(): Products
    @GET("products/search")
    suspend fun getProductsByName(@Query("q") name: String): Products
    @GET("products/{id}")
    suspend fun getCardById(@Path("id") id: Int): Product
}