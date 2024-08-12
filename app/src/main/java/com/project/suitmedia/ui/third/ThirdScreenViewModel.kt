package com.project.suitmedia.ui.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.project.suitmedia.api.UserResponseItem
import com.project.suitmedia.repository.UserRepository

class ThirdScreenViewModel(userRepository: UserRepository) : ViewModel() {
    val user: LiveData<PagingData<UserResponseItem>> =
        userRepository.getUsers().cachedIn(viewModelScope)
}