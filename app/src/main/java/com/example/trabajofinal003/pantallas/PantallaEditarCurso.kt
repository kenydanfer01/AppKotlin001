package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.*
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaEditarCurso(navController: NavController, id_curso: String?){
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
                Text(text = "Información del Curso")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaEditarCurso(navController, id_curso)
    }
}

@Composable
fun BodyPantallaEditarCurso(navController: NavController, id_curso: String?) {
    val contexto = LocalContext.current
    var nombreCurso by remember { mutableStateOf("") }
    var id_docente by remember { mutableStateOf("") }
    var nombreDocente by remember { mutableStateOf("") }
    var apellidoDocente by remember { mutableStateOf("") }
    var cantidadAlumnos by remember { mutableStateOf("") }

    ObtenerInfoCurso(id_curso, respuesta = {
        if (it != null) {
            nombreCurso = it.nombreCurso
            id_docente = it.id_docente
            nombreDocente = it.nomDocente
            apellidoDocente = it.apeDocente
            cantidadAlumnos = it.cantidadAlumnos
        }
    }, contexto = contexto )

    Column(modifier = Modifier.fillMaxSize()
    ) {
        TextoV01(texto = "CURSO: $nombreCurso", size_sp = 24)
        TextoV01(texto = "DOCENTE: $nombreDocente $apellidoDocente" , size_sp = 20)
        ObtenerAlumnosCurso(id_curso = id_curso, contexto = contexto)
        Spacer(modifier = Modifier.padding(16.dp))
        Button(modifier = Modifier.padding(start = 250.dp),
            onClick = {
                /* Al dar click vamos a la Pantalla de Agregar Alumno */
                navController.navigate(AppPantallas.PantallaAgregarAlumnoCurso.route + "/$id_curso")
            }) {
            Text(text = "Agregar Alumno")
        }
        TextoV01(texto = "LISTA DE ALUMNOS" , size_sp = 20)

        LazyColumn() {
            var count = 0;
            items(alumnosCurso) { alumno ->
                count++
                Card(
                    elevation = 2.dp,
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                ) {
                    Column() {
                        Text(text = "$count) ${alumno.apellidos}, ${alumno.nombres}."  , fontSize = 20.sp)
                    }
                }
            }
        }
    }
}



