package com.partsrequestmanager.viewmodels

// app/viewmodels/RequestViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partsrequestmanager.data.AppDatabase
import com.partsrequestmanager.data.Request
import kotlinx.coroutines.launch

class RequestViewModel(private val db: AppDatabase) : ViewModel() { // Убедитесь, что наследуете от ViewModel
    // Добавление новой заявки
    fun addRequest(request: Request, callback: () -> Unit) {
        viewModelScope.launch {
            db.requestDao().addRequest(request)
            callback()
        }
    }

    // Получение всех заявок
    fun getAllRequests(callback: (List<Request>) -> Unit) {
        viewModelScope.launch {
            val requests = db.requestDao().getAllRequests()
            callback(requests)
        }
    }
}