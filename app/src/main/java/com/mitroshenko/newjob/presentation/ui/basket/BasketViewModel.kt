package com.mitroshenko.newjob.presentation.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.mitroshenko.newjob.data.repository.Entity
import com.mitroshenko.newjob.data.repository.ProductRepository
import com.mitroshenko.newjob.presentation.ui.card.ProductCardViewModel
import kotlinx.coroutines.launch

class BasketViewModel(private val repository: ProductRepository) : ViewModel() {

    val allProducts: LiveData<List<Entity>> = repository.allProducts.asLiveData()
    fun delete(position: Int) = viewModelScope.launch {
        val prod = allProducts.value?.get(position)
        if (prod !== null) {
            repository.delete(prod)
        }
    }
}
class BasketViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BasketViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}