package com.project.suitmedia.ui.main

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.suitmedia.ui.second.SecondScreenActivity

class MainActivityViewModel : ViewModel() {

    private val _isPalindrome = MutableLiveData<Boolean>()
    val isPalindrome: LiveData<Boolean> get() = _isPalindrome

    private val _navigateToSecondScreen = MutableLiveData<Boolean>()
    val navigateToSecondScreen: LiveData<Boolean> get() = _navigateToSecondScreen

    fun checkPalindrome(text: String) {
        val reversedText = text.reversed()
        _isPalindrome.value = text.equals(reversedText, ignoreCase = true)
    }

    fun checkNameAndNavigate(name: String): Boolean {
        return if (name.isNotEmpty()) {
            _navigateToSecondScreen.value = true
            true
        } else {
            false
        }
    }

    fun onNavigatedToSecondScreen() {
        _navigateToSecondScreen.value = false
    }
}