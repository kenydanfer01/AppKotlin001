package com.example.trabajofinal003

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trabajofinal003.AppNavegacion.AppNavegacion
import com.example.trabajofinal003.ui.theme.TrabajoFinal003Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrabajoFinal003Theme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    AppNavegacion()
//                    ClickableText()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TrabajoFinal003Theme {
        AppNavegacion()
    }
}
//@Composable
//fun ClickableText() {
//    var showPopup by remember { mutableStateOf(false) }
//    Column(Modifier.clickable(onClick = { showPopup = true }), content = {
//        Card(
//            shape = RoundedCornerShape(4.dp), modifier = Modifier.padding(8.dp),
//            backgroundColor = Color.LightGray
//        ) {
//            Text(
//                text = "Click to see dialog", modifier = Modifier.padding(16.dp),
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily.Serif
//                )
//            )
//        }
//    })
//    val onPopupDismissed = { showPopup = false }
//    if (showPopup) {
//        AlertDialog(
//            onDismissRequest = onPopupDismissed,
//            text = {
//                Text("Congratulations! You just clicked the text successfully")
//            },
//            confirmButton = {
//                Button(
//                    onClick = onPopupDismissed
//                ) {
//                    Text(text = "Ok")
//                }
//            })
//    }
//}
//
//@Preview
//@Composable
//fun ClickableTextPreview() {
//    Column {
//        com.example.trabajofinal003.pantallas.ClickableText()
//    }
//}