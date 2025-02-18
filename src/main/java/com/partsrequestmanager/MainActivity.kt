import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.partsrequestmanager.data.AppDatabase
import com.partsrequestmanager.data.AuthViewModelFactory
import com.partsrequestmanager.data.RequestViewModelFactory
import com.partsrequestmanager.data.User
import com.partsrequestmanager.ui.AddRequestScreen
import com.partsrequestmanager.ui.LoginScreen
import com.partsrequestmanager.ui.RegistrationScreen
import com.partsrequestmanager.viewmodels.AuthViewModel
import com.partsrequestmanager.viewmodels.RequestViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация базы данных
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "esc_database"
        ).build()

        setContent {
            // Создаем NavController для управления навигацией
            val navController = rememberNavController()

            // Состояние авторизации
            var isLoggedIn by remember { mutableStateOf(false) }
            var currentUsername by remember { mutableStateOf("") }

            // Навигационные маршруты
            NavHost(navController = navController, startDestination = if (isLoggedIn) "request_list" else "login") {
                // Экран входа
                composable("login") {
                    // Явное создание AuthViewModel
                    val authViewModel: AuthViewModel = viewModel(
                        modelClass = AuthViewModel::class.java,
                        factory = AuthViewModelFactory(db)
                    )

                    // Явное указание типа для лямбды
                    LoginScreen(authViewModel = authViewModel) { user: User? ->
                        if (user != null) {
                            currentUsername = user.username
                            isLoggedIn = true
                            navController.navigate("request_list")
                        }
                    }
                }

                // Экран регистрации
                composable("registration") {
                    val authViewModel: AuthViewModel = viewModel(
                        modelClass = AuthViewModel::class.java,
                        factory = AuthViewModelFactory(db)
                    )

                    RegistrationScreen(authViewModel = authViewModel) {
                        isLoggedIn = true
                        navController.navigate("request_list")
                    }
                }

                // Экран списка заявок
                composable("request_list") {
                    val requestViewModel: RequestViewModel = viewModel(
                        modelClass = RequestViewModel::class.java,
                        factory = RequestViewModelFactory(db)
                    )

                    RequestListScreen(requestViewModel = requestViewModel, onAddRequestClick = {
                        navController.navigate("add_request")
                    })
                }

                // Экран создания новой заявки
                composable("add_request") {
                    val requestViewModel: RequestViewModel = viewModel(
                        modelClass = RequestViewModel::class.java,
                        factory = RequestViewModelFactory(db)
                    )

                    AddRequestScreen(requestViewModel = requestViewModel) {
                        navController.navigate("request_list")
                    }
                }
            }
        }
    }
}