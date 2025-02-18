package com.partsrequestmanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.partsrequestmanager.data.Request
import com.partsrequestmanager.viewmodels.RequestViewModel

@Composable
fun AddRequestScreen(requestViewModel: RequestViewModel, onAdded: () -> Unit) {
    var type by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var items by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = type, onValueChange = { type = it }, label = { Text("Тип работы") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Описание") })
        TextField(value = items, onValueChange = { items = it }, label = { Text("Необходимые предметы") })

        Button(onClick = {
            if (type.isNotEmpty() && description.isNotEmpty() && items.isNotEmpty()) {
                val newRequest = Request(type = type, description = description, items = items)
                requestViewModel.addRequest(newRequest) { // Передаем callback
                    onAdded() // Вызываем обратный вызов после добавления заявки
                }
            } else {
                println("Заполните все поля!")
            }
        }) {
            Text("Создать заявку")
        }
    }
}