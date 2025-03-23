package com.mitroshenko.newjob.presentation.ui.search

import android.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.data.model.product.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchViewModel : ViewModel() {

    val productList: MutableLiveData<List<Product>> = MutableLiveData()
    val client = OkHttpClient.Builder()
        .build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()
    val sf_Api = retrofit.create(ProductApi::class.java)
    val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(text: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(text: String?): Boolean {
            CoroutineScope(Dispatchers.IO).launch {
                val list = text?.let { sf_Api.getProductsByName(it) }
                productList.postValue(list!!.products)
            }
            return true
        }
    }

    fun loadProducts() {
        viewModelScope.launch {
            val list = sf_Api.getAllProducts()
            productList.postValue(list.products)
        }
    }
}
