package com.example.application.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "TechLingo"
    }
    val text: LiveData<String> = _text
    private val _text2 = MutableLiveData<String>().apply {
        value = "Инновации в языке"
    }
    val text2: LiveData<String> = _text2
}