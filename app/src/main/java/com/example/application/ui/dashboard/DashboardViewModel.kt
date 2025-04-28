package com.example.application.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Изучайте английский, проходя тесты!"
    }
    val text: LiveData<String> = _text

    private val _newTest = MutableLiveData<String>().apply {
        value = "Мы работаем над улучшением нашего продукта и скоро добавим новые тестовые материалы. Будьте готовы к обновлению!"
    }
    val newTest: LiveData<String> = _newTest
}