package com.mitroshenko.newjob.presentation.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.data.api.model.product.Product
import com.mitroshenko.newjob.data.api.model.product.Review
import com.mitroshenko.newjob.domain.usecases.LoadProductCard
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardViewModel : ViewModel() {

    val loadProductCard = LoadProductCard()
    val reviewsList: MutableLiveData<List<Review>> = MutableLiveData()
    val cardList: MutableLiveData<List<Product>> = MutableLiveData()
    val client = OkHttpClient.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val sf_Api = retrofit.create(ProductApi::class.java)

    fun loadReviews(id: Int) {
        viewModelScope.launch {
            val list = sf_Api.getCardById(id)
            reviewsList.postValue(list.reviews)
        }
    }

    fun loadCard(id: Int) {
        viewModelScope.launch {
            val card = sf_Api.getCardById(id)
            loadProductCard.execute(card)
        }
    }
}