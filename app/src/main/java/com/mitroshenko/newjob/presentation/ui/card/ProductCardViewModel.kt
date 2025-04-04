package com.mitroshenko.newjob.presentation.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.data.database.ProductsApplications
import com.mitroshenko.newjob.data.model.product.Product
import com.mitroshenko.newjob.data.model.product.Review
import com.mitroshenko.newjob.data.repository.Entity
import com.mitroshenko.newjob.data.repository.ProductRepository
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardViewModel(private val repository: ProductRepository) : ViewModel() {


//    val allProducts: LiveData<List<Entity>> = repository.allProducts.asLiveData()

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
    fun insert(prod: Entity) = viewModelScope.launch {
        repository.insert(prod)
    }
}
class ProductCardViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProductCardViewModel::class.java)) {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as ProductsApplications
            @Suppress("UNCHECKED_CAST")
            return ProductCardViewModel(application.repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}