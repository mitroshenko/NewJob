package com.mitroshenko.newjob.presentation.ui.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.mitroshenko.newjob.data.database.Applications
import com.mitroshenko.newjob.data.repository.favourites.FavouriteEntity
import com.mitroshenko.newjob.data.repository.favourites.FavouriteRepository
import kotlinx.coroutines.launch

class FavouriteViewModel(private val repository: FavouriteRepository) : ViewModel() {

    val allFavourite: LiveData<List<FavouriteEntity>> = repository.allFavourite.asLiveData()
    fun delete(position: Int) = viewModelScope.launch {
        val fav = allFavourite.value?.get(position)
        if (fav !== null) {
            repository.delete(fav)
        }
    }
}
class FavouriteViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(FavouriteViewModel::class.java)) {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]) as Applications
            @Suppress("UNCHECKED_CAST")
            return FavouriteViewModel(application.favouriterepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}