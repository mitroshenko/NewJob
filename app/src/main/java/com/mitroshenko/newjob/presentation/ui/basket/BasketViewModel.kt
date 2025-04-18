package com.mitroshenko.newjob.presentation.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mitroshenko.newjob.data.database.Applications
import com.mitroshenko.newjob.data.repository.basket.BasketEntity
import com.mitroshenko.newjob.data.repository.basket.BasketRepository
import kotlinx.coroutines.launch

class BasketViewModel(private val repository: BasketRepository) : ViewModel() {

    val allProducts: LiveData<List<BasketEntity>> = repository.allProducts.asLiveData()
    fun delete(position: Int) = viewModelScope.launch {
        val prod = allProducts.value?.get(position)
        if (prod !== null) {
            repository.delete(prod)
        }
    }
}
class BasketViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(BasketViewModel::class.java)) {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as Applications
            @Suppress("UNCHECKED_CAST")
            return BasketViewModel(application.basketrepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}