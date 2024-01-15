package uz.domain.composelesson.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PersonDetailScreen(data: Pair<Int, String>) {
    Column {
        Image(
            painter = painterResource(id = data.first),
            contentDescription = data.second,
            modifier = Modifier.fillMaxWidth().size(200.dp)
        )
        Text(
            text = data.second,
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = "Детальная информация про ${data.second}",
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

