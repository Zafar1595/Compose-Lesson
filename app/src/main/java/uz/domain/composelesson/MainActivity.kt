package uz.domain.composelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uz.domain.composelesson.ui.MainViewModel
import uz.domain.composelesson.ui.PersonDetailScreen
import uz.domain.composelesson.ui.PersonsListScreen
import uz.domain.composelesson.ui.ProfileScreen
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
    ComposeLessonTheme(darkTheme = false) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            val viewModel = remember { MainViewModel() }
            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        bottomBarState = bottomBarState
                    )
                },
                content = { padding ->
                    NavigationHost(
                        navController = navController,
                        viewModel = viewModel,
                        padding = padding
                    )
                }
            )
        }
    }
}

@Composable
fun NavigationHost(
    navController: NavHostController,
    viewModel: MainViewModel,
    padding: PaddingValues,
) {

    NavHost(
        navController,
        startDestination = BottomNavItem.Home.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(BottomNavItem.Home.route) {
            PersonsListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.PersonDetailScreen.route + "/{data}",
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) { backStackEntry ->
            val data = backStackEntry.arguments?.getString("data")
            PersonDetailScreen(data = Pair(R.drawable.baseline_person_24, data ?: ""))
        }
        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, bottomBarState: MutableState<Boolean>) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        backgroundColor = Color.Green
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            val selected = currentRoute == item.route
            BottomNavigationItem(
                selected = selected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painterResource(id = item.icon),
                        contentDescription = item.label,
                        tint = if(selected) Color.White else Color.Black
                    )
                },
                label = { Text(item.label, color = if(selected) Color.White else Color.Black) }
            )
        }
    }
}

enum class Screen(val route: String) {
    PersonsListScreen("PersonListScreen"),
    PersonDetailScreen("PersonDetailScreen"),
    ProfileScreen("ProfileScreen")

}

sealed class BottomNavItem(
    val route: String,
    val icon: Int,
    val icon_selected: Int,
    val label: String
) {
    data object Home :
        BottomNavItem(
            Screen.PersonsListScreen.route,
            R.drawable.baseline_add_home_24,
            R.drawable.baseline_add_home_24_selected,
            "Home"
        )

    data object Profile :
        BottomNavItem(
            Screen.ProfileScreen.route,
            R.drawable.baseline_adb_24,
            R.drawable.baseline_adb_24_selected,
            "Profile"
        )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    App()
}


