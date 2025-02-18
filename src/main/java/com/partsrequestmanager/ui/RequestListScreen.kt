// app/ui/RequestListScreen.kt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.foundation.layout.* // Импортируем Spacer и другие компоненты
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.partsrequestmanager.data.Request
import com.partsrequestmanager.viewmodels.RequestViewModel

@Composable
fun RequestListScreen(
    requestViewModel: RequestViewModel,
    onAddRequestClick: () -> Unit // Параметр для обработки клика
) {
    // Используем mutableStateListOf вместо emptyList
    val requests = remember { mutableStateListOf<Request>() }

    LaunchedEffect(Unit) {
        requestViewModel.getAllRequests { result ->
            requests.clear() // Очищаем список перед добавлением новых данных
            requests.addAll(result) // Добавляем результат в список
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text("Список заявок", style = MaterialTheme.typography.headlineMedium)

        if (requests.isEmpty()) {
            Text("Заявок пока нет.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(requests) { request ->
                    Card(modifier = Modifier.padding(8.dp)) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "Тип: ${request.type}")
                            Text(text = "Описание: ${request.description}")
                            Text(text = "Предметы: ${request.items}")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp)) // Используем Spacer для отступа

        Button(onClick = onAddRequestClick, modifier = Modifier.padding(16.dp)) {
            Text("Добавить заявку")
        }
    }
}