package com.mitroshenko.newjob.data.api.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class IdCardModel : ViewModel() {
    val idCard : MutableLiveData<Int> = MutableLiveData<Int>()
}