package com.mitroshenko.newjob.presentation.ui.card

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductCardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is card Fragment"
    }
    val text: LiveData<String> = _text
}