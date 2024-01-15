package uz.domain.composelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.domain.composelesson.ui.MainViewModel
import uz.domain.composelesson.ui.PersonDetailScreen
import uz.domain.composelesson.ui.PersonsListView
import uz.domain.composelesson.ui.theme.ComposeLessonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
private fun App() {
    ComposeLessonTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val viewModel = remember { MainViewModel() }
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.PersonsListScreen.route
            ) {
                composable(route = Screen.PersonsListScreen.route) {
                    PersonsListView(navController, viewModel)
                }
                composable(route = Screen.PersonDetailScreen.route + "/{data}",
                    arguments = listOf(navArgument("data") { type = NavType.StringType })
                ) { backStackEntry ->
                    val data = backStackEntry.arguments?.getString("data")
                    PersonDetailScreen(data = Pair(R.drawable.baseline_person_24, data ?: ""))
                }
            }

        }
    }
}

enum class Screen(val route: String) {
    PersonsListScreen("PersonListScreen"),
    PersonDetailScreen("PersonDetailScreen")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}


