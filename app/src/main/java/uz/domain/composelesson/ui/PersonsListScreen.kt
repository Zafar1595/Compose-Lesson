package uz.domain.composelesson.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import uz.domain.composelesson.R
import uz.domain.composelesson.Screen

@Composable
fun PersonsListScreen(navController: NavHostController, viewModel: MainViewModel) {

    val data = viewModel.getItems()

        Column(modifier = Modifier
            .fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Persons List",
                    modifier = Modifier
                        .padding(16.dp),
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Image(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Settings",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(16.dp)
                        .clickable {
                            Toast
                                .makeText(navController.context, "Settings", Toast.LENGTH_SHORT)
                                .show()
                        })
            }

            LazyColumn(
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 8.dp,
                    bottom = 0.dp
                )
            ) {
                items(data.size) { i ->
                    Greeting(onClick = {
                        navController.navigate("${Screen.PersonDetailScreen.route}/${data[i]}")
                    }, item = data[i])
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }



}

@Composable
fun Greeting(modifier: Modifier = Modifier, onClick: () -> Unit, item: String) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(0.dp, 4.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = item,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
        )

        Column {
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = item,
                modifier = modifier,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )

            Text(
                text = "Person Phone Number",
                modifier = modifier,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )
        }
    }
}
