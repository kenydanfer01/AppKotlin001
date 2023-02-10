package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun SegundaPantalla(navController: NavController, text: String?){
    Scaffold(
        topBar = {
            TopAppBar() {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Segunda Pantalla")
            }
        }
    ) {

        BodyContent2(navController, text)
    }
}

@Composable
fun BodyContent2(navController: NavController, text: String?){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola Navegación")
        text?.let{
            Text(it)
        }
        Button(onClick = {
            navController.navigate(route = AppPantallas.LoginPantalla.route)
        }) {
            Text(text = "Volver atrás")
        }
    }
}
