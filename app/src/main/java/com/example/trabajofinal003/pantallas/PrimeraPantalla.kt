package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PrimeraPantalla(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar() {
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Primera Pantalla")
            }
        }
    ) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hola Navegación")
        Button(onClick = {
            navController.navigate(route = AppPantallas.SegundaPantalla.route + "/Este es un parámetro")
        }) {
            Text(text = "Navega")
        }
    }
}
