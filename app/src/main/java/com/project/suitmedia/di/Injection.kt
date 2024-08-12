package com.project.suitmedia.di

import android.content.Context
import com.project.suitmedia.api.ApiConfig
import com.project.suitmedia.repository.UserRepository

object Injection {
    fun provideRepository(context: Context) : UserRepository {
        val apiService = ApiConfig.getApiService()

        return UserRepository(apiService)
    }
}