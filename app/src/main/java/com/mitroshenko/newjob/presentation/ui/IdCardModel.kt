package com.mitroshenko.newjob.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class IdCardModel : ViewModel() {
    val idCard : MutableLiveData<Int> = MutableLiveData<Int>()
}