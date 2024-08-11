package com.project.suitmedia.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val _isPalindrome = MutableLiveData<Boolean>()
    val isPalindrome: LiveData<Boolean> get() = _isPalindrome

    fun checkPalindrome(text: String) {
        val reversedText = text.reversed()
        _isPalindrome.value = text.equals(reversedText, ignoreCase = true)
    }
}