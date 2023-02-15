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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.ObtenerCursosAlumno
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.cursosAlumno
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaAlumno(navController: NavController, id_usuario: String?){
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
                Text(text = "BIENVENIDO ALUMNO")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaAlumno(id_usuario, navController)
    }
}

@Composable
fun BodyPantallaAlumno(id_usuario: String?, navController: NavController) {
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
        TextoV01(texto = "Bienvenido Alumno:", size_sp = 25)
        TextoV01(texto = nombres, size_sp = 30)
        TextoV01(texto = apellidos, size_sp = 30)
        /* Boton para acceder a la pantalla de Gestionar la Cuenta del Usuario */
        Button( modifier = Modifier.align(Alignment.End).padding(15.dp),onClick = {
            navController.navigate(route = AppPantallas.PantallaGestionarCuenta.route + "/$id_usuario")
        }) { Text(text = "Gestionar Mi Cuenta") }
        ObtenerCursosAlumno(id_usuario = id_usuario, contexto = contexto)
        Spacer(modifier = Modifier.padding(8.dp))
        TextoV01(texto = "Usted está inscrito en los Siguientes Cursos: ", size_sp = 25)
        LazyColumn(){
            items(cursosAlumno) { cursoA ->
                Card(
                    elevation = 5.dp,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable{
                            navController.navigate(route = AppPantallas.PantallaVerNotas.route + "/${cursoA.id}")
                        }
                ) {
                    Column() {
                        Text(text = "Curso: " + cursoA.nombreCurso, fontSize = 20.sp)
                        Text(text = "Docente: " + cursoA.nomDocente +", "+ cursoA.apeDocente, fontSize = 20.sp)
                    }
                }
            }
        }
    }
}

