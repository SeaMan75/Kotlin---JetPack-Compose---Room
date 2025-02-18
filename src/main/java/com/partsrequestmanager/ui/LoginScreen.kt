package com.partsrequestmanager.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.partsrequestmanager.data.User
import com.partsrequestmanager.viewmodels.AuthViewModel

@Composable
fun LoginScreen(authViewModel: AuthViewModel, onLoginSuccess: (User?) -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Логин") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                authViewModel.authenticate(username, password) { user: User? ->
                    if (user != null) {
                        onLoginSuccess(user) // Передаем пользователя в callback
                    } else {
                        println("Неверный логин или пароль")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Войти")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = { /* Открываем RegistrationScreen */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Зарегистрироваться")
        }
    }
}