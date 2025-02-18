package com.partsrequestmanager.viewmodels

// app/viewmodels/AuthViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.partsrequestmanager.data.AppDatabase
import com.partsrequestmanager.data.User
import kotlinx.coroutines.launch

class AuthViewModel(private val db: AppDatabase) : ViewModel() { // Убедитесь, что наследуете от ViewModel
    // Регистрация нового пользователя
    fun registerUser(user: User, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            db.userDao().registerUser(user)
            callback(true) // Упрощенная реализация
        }
    }

    // Авторизация пользователя
    fun authenticate(username: String, password: String, callback: (User?) -> Unit) {
        viewModelScope.launch {
            val user = db.userDao().getUser(username, password)
            callback(user)
        }
    }
}