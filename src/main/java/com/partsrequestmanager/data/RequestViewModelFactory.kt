package com.partsrequestmanager.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.partsrequestmanager.viewmodels.RequestViewModel

class RequestViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RequestViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RequestViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}