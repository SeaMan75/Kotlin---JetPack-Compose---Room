package com.partsrequestmanager.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.partsrequestmanager.viewmodels.AuthViewModel

class AuthViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
