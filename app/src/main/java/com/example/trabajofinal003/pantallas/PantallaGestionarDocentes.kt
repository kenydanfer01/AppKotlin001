package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun PantallaGestionarDocentes(navController: NavController){
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
                Text(text = "Gestionar Alumnos")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaGestionarDocentes(navController)
    }
}

@Composable
fun BodyPantallaGestionarDocentes(navController: NavController) {
    val contexto = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()
    ) {
        ObtenerListaUsuarios(id_rol = "2", contexto = contexto)
        Button(modifier = Modifier.padding(start = 250.dp),
            onClick = {
                /* Al dar click vamos a la Pantalla de Agregar Alumno */
                navController.navigate(AppPantallas.PantallaAgregarDocente.route)
            }) {
            Text(text = "Agregar Docente")
        }
        /* Spacer sólo es un espacio en blanco, vacío */
        Spacer(modifier = Modifier.padding(16.dp))
        /* TextoV01 es una función para mostrar Texto */
        TextoV01(texto = "LISTA DE DOCENTES:" , size_sp = 20)
        /* LazyColumn es una columna que permite desplazamiento o Scroll */
        LazyColumn() {
            var count = 0;
            items(listaUsuarios) { usuario ->
                count++
                Card(
                    elevation = 1.dp,
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth()
                ) {
                    Row (horizontalArrangement = Arrangement.SpaceBetween){
                        Text(text = "$count) ${usuario.dni} - ${usuario.apellidos}."  , fontSize = 18.sp)
                        Icon(imageVector = Icons.Default.Edit,
                            contentDescription = "Pen",
                            modifier = Modifier.padding(horizontal = 10.dp) .clickable {
                                //
                            })
                    }
                }
            }
        }
    }
}




