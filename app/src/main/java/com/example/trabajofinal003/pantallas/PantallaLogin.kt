@file:Suppress("NAME_SHADOWING")

package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.ValidarLogin
import com.example.trabajofinal003.R
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaLogin(navController: NavController){
    Scaffold (topBar = {
        TopAppBar {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        navController.popBackStack()
                    })
            Text(text = "ACCESO AL SISTEMA DE GESTIÓN ACADÉMICA")
        }
    }
    ){
        BodyLogin(navController)
    }
}

@Composable
fun BodyLogin(navController: NavController){
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagenLogin(id = R.drawable.logo_sm)
            TextoPrincipal(texto = "INICIO DE SESIÓN")
            Login(navController)
        }
}
@Composable
fun Login(navController: NavController) {
    val contexto = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var dni by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
        var mensaje by remember { mutableStateOf("") }
        var id_rol :String
        var id: String
        var showPopup by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = {
                Text("DNI")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = {
                Text("Clave PIN")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Button(
            onClick = {
                ValidarLogin(
                    dni = dni,
                    clave = clave,
                    respuesta = {
                        if (it!=null) {
                            mensaje = ""
                            id_rol = it.id_rol
                            id = it.id
                            when(id_rol){
                                "1" -> navController.navigate(route = AppPantallas.PantallaDirector.route + "/$id")
                                "2" -> navController.navigate(route = AppPantallas.PantallaDocente.route + "/$id")
                                "3" -> navController.navigate(route = AppPantallas.PantallaAlumno.route + "/$id")
                            }
                        } else {
                            mensaje = ""
                            showPopup = true
                        }
                    },
                    contexto = contexto
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 15.dp)
        ) {
            Text(text = "INGRESAR")
        }
        Text(text = mensaje)
        val onPopupDismissed = { showPopup = false }
        if (showPopup) {
            AlertDialog(
                onDismissRequest = onPopupDismissed,
                text = {
                    Text("DNI y/o CLAVE Inválidos. Por Favor verifique sus datos.", fontSize = 28.sp)
                },
                confirmButton = {
                    Button(
                        onClick = onPopupDismissed
                    ) {
                        Text(text = "Reintentar")
                    }
                })
        }
    }
}



@Composable
fun ImagenLogin(id: Int){
    Image(painter = painterResource(id = id),
        contentDescription = "Login" ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(190.dp)
    )
}
