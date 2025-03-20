package com.mitroshenko.newjob.data.api.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageUrl: ViewModel() {
    val imageUrl: MutableLiveData<String> = MutableLiveData<String>()
}