package com.partsrequestmanager.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.partsrequestmanager.viewmodels.AuthViewModel
import com.partsrequestmanager.data.User

// app/ui/RegistrationScreen.kt
@Composable
fun RegistrationScreen(authViewModel: AuthViewModel, onRegisterSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = username, onValueChange = { username = it }, label = { Text("Логин") })
        TextField(value = password, onValueChange = { password = it }, label = { Text("Пароль") })

        Button(onClick = {
            val user = User(username = username, password = password)
            authViewModel.registerUser(user) {
                onRegisterSuccess()
            }
        }) {
            Text("Зарегистрироваться")
        }
    }
}