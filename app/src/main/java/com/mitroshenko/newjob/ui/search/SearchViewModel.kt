package com.mitroshenko.newjob.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mitroshenko.newjob.retrofit.product.Product
import com.mitroshenko.newjob.retrofit.product.Products

//class SearchViewModel(products: Products) : ViewModel() {
//
//        val productList: LiveData<List<Product>> = products.products.asL}

class SearchViewModel : ViewModel() {

        val productList: MutableLiveData<List<Product>> by lazy {
                MutableLiveData<List<Product>>()
        }
//        val idCard: MutableLiveData<Int> by lazy {
//                MutableLiveData<Int>()
//        }
}
