package com.mitroshenko.newjob.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mitroshenko.newjob.data.api.model.product.Product

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
