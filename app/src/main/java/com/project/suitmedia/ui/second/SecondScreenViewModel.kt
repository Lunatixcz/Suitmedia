package com.project.suitmedia.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SecondScreenViewModel : ViewModel() {

    private val _selectedUsername = MutableLiveData<String>()
    val selectedUsername : LiveData<String> get() = _selectedUsername

    fun setSelectedUsername(name: String) {
        _selectedUsername.value = name
    }
}