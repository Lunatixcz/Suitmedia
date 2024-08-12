package com.project.suitmedia.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.project.suitmedia.api.ApiService
import com.project.suitmedia.api.UserResponseItem
import com.project.suitmedia.data.UserPagingSource

class UserRepository (
    private val apiService: ApiService) {

    fun getUsers() : LiveData<PagingData<UserResponseItem>> {
        Log.d("UserRepository", "getUsers called")
        return Pager(
            config = PagingConfig(
                pageSize = 5,
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}