package com.example.application.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Здесь отображается Ваша статистика"
    }
    val text: LiveData<String> = _text

    val email = MutableLiveData<String>()

    val tests = MutableLiveData<String>()
    val answers = MutableLiveData<String>()
    val percent = MutableLiveData<String>()
    fun setUserEmail() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            email.value = "Пользователь: " + FirebaseAuth.getInstance().currentUser!!.email
        }
    }
    fun setUserData() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).get()
                .addOnSuccessListener {
                    val t = it.getDouble("tests")!!.toDouble()
                    val a = it.getDouble("answers")!!.toDouble()
                    val number = (a / (t * 10)) * 100

                    percent.value = "Процент правильных ответов: " + (Math.round(number * 100.0) / 100.0).toString() + "%"
                    tests.value = "Пройдено тестов: " + t.toInt().toString()
                    answers.value = "Правильных ответов: " + a.toInt().toString()

                }
        }
    }
}