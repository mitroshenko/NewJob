package com.mitroshenko.newjob.presentation.ui.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.data.model.product.Product
import com.mitroshenko.newjob.data.model.product.Review
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardViewModel : ViewModel() {

    val reviewsList: MutableLiveData<List<Review>> = MutableLiveData()
    val productList: MutableLiveData<Product> = MutableLiveData()
    val client = OkHttpClient.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val sf_Api = retrofit.create(ProductApi::class.java)

    fun loadReviews(id: Int) {
        viewModelScope.launch {
            val list = sf_Api.getReviewsById(id)
            reviewsList.postValue(list.reviews)
        }
    }
    fun loadCard(id: Int) {
        viewModelScope.launch {
            val product = sf_Api.getCardById(id)
            productList.postValue(product)
        }
    }
}