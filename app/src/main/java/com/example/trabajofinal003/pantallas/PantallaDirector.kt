package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.ObtenerCursosProfesor
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.Usuario
import com.example.trabajofinal003.AccesoDatos.cursosProfesor
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaDirector(navController: NavController, id_usuario: String?){
    Scaffold(
        topBar = {
            TopAppBar() {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "BIENVENIDO DIRECTOR")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaDirector(id_usuario, navController)
    }
}

@Composable
fun BodyPantallaDirector(id_usuario: String?, navController: NavController) {
    val contexto = LocalContext.current
    var nombres by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }


    ObtenerUsuario(id_usuario, respuesta = {
        if (it != null) {
            nombres = it.nombres
            apellidos = it.apellidos
        }
    }, contexto = contexto )
    Column(modifier = Modifier.fillMaxSize()
    ) {
        TextoV01(texto = "Bienvenido director:", size_sp = 36)
        TextoV01(texto = nombres, size_sp = 30)
        TextoV01(texto = apellidos, size_sp = 30)
        Column(modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            /* Boton para acceder a la pantalla de Gestionar Alumnos */
            Button(onClick = {
                navController.navigate(route = AppPantallas.PantallaGestionarAlumnos.route)
            }) { Text(text = "Gestionar Alumnos") }
            Spacer(modifier = Modifier.padding(16.dp))

            /* PARA ACCEDER A PANTALLA DE GESTIONAR DOCENTES */
            Button(onClick = {
                navController.navigate(AppPantallas.PantallaGestionarDocentes.route)
            }) { Text(text = "Gestionar Docentes") }
            Spacer(modifier = Modifier.padding(16.dp))

            /* PARA ACCEDER A PANTALLA DE GESTIONAR CURSOS */
            Button(onClick = {
                navController.navigate(AppPantallas.PantallaGestionarCursos.route)
            }) { Text(text = "Gestionar Cursos") }
            Spacer(modifier = Modifier.padding(16.dp))

            /* Boton para acceder a la pantalla de Gestionar la Cuenta del Usuario */
            Button(onClick = {
                navController.navigate(route = AppPantallas.PantallaGestionarCuenta.route + "/$id_usuario")
            }) { Text(text = "Gestionar Mi Cuenta") }
        }

    }
}



