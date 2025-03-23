package com.mitroshenko.newjob.data.model.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ImageUrlModel: ViewModel() {
    val imageUrl: MutableLiveData<String> = MutableLiveData<String>()
}