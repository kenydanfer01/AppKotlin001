package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.navigation.NavController
import com.example.trabajofinal003.R
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaPrincipal(navController: NavController) {
    Scaffold {
        BodyPrincipal(navController)
    }
}

@Composable
fun BodyPrincipal(navController: NavController){
    LazyColumn(modifier = Modifier.fillMaxSize()
    ) {
        item {
            Espacio(24)
            TextoPrincipal("SISTEMA DE GESTIÓN ACADÉMICA")
            Espacio(16)
            ImagenPrincipal(R.drawable.principal)
            Espacio(16)
            TextoPrincipal("SAN MIGUEL DE PIURA")
            ImagenSecundario(id = R.drawable.logo_sm)
            SimpleButton(navController)
            SimpleButton2(navController)
        }

    }
}

@Composable
fun TextoPrincipal(texto: String){
    Text(text = texto,
        fontSize = 35.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ImagenPrincipal(id: Int){
    Image(painter = painterResource(id = id),
        contentDescription = "Logo Colegio" ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    )
}

@Composable
fun Espacio(numero: Int){
    Spacer(modifier = Modifier.height(numero.dp))
}

@Composable
fun ImagenSecundario(id: Int){
    Image(painter = painterResource(id = id),
        contentDescription = "Logo Colegio" ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(132.dp)
    )
}
@Composable
fun SimpleButton(navController: NavController) {
    Button(onClick = {
        //your onclick code here
        navController.navigate(route = AppPantallas.PantallaLogin.route)
    },
    modifier = Modifier
        .fillMaxSize()
        .padding(30.dp, 5.dp)
    ) {
        Text(text = "Ingreso al sistema")
    }
}

@Composable
fun SimpleButton2(navController: NavController) {
    Button(onClick = {
        //your onclick code here
        navController.navigate(route = AppPantallas.PantallaInfoColegio.route)
    },
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp, 5.dp)
    ) {
        Text(text = "Ver Información")
    }
}