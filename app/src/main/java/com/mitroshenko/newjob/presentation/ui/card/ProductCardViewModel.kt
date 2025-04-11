package com.mitroshenko.newjob.presentation.ui.card

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mitroshenko.newjob.data.api.ProductApi
import com.mitroshenko.newjob.data.database.Applications
import com.mitroshenko.newjob.data.model.product.Product
import com.mitroshenko.newjob.data.model.product.Review
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity
import com.mitroshenko.newjob.data.repository.favourites.FavouriteRepository
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductCardViewModel(private val basketrepository: BasketRepository, private val favouriterepository: FavouriteRepository) : ViewModel() {
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
    fun insertBasket(id: Int) = viewModelScope.launch {
        val product = sf_Api.getCardById(id)
        basketrepository.insert(basketEntity = BasketEntity(
            title = product.title,
            brand = product.brand,
            rating = product.rating.toString(),
            price = product.price.toString(),
            images = product.images.toString(),
            idCardProd = product.id
        ))
    }
    fun insertFavourite(id: Int) = viewModelScope.launch {
        val product = sf_Api.getCardById(id)
        favouriterepository.insert(favouriteEntity = FavouriteEntity(
            title = product.title,
            brand = product.brand,
            rating = product.rating.toString(),
            price = product.price.toString(),
            images = product.images.toString(),
            idCardProd = product.id
        ))
    }
}
class ProductCardViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ProductCardViewModel::class.java)) {
            val applicationFavourite = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as Applications
            val applicationBasket = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as Applications
            @Suppress("UNCHECKED_CAST")
            return ProductCardViewModel(applicationBasket.basketrepository, applicationFavourite.favouriterepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}