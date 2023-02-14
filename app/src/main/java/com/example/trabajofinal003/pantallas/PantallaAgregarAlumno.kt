package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.insertarUsuario
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaAgregarAlumno(navController: NavController){
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
                Text(text = "Registrar Nuevo Alumno")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaAgregarAlumno(navController)
    }
}

@Composable
fun BodyPantallaAgregarAlumno(navController: NavController){
    val contexto = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var dni by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("123456") }
        var nombres by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var mensaje by remember { mutableStateOf("") }
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("CLAVE") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("apellidos") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true
        )
        Button(
            onClick = {
                /* Llamamos a la Función insertarUsuario, y le envíamos los parámetros necesarios
                * Nótese que en id_rol se envía el 3, ya que se trata de Alumnos.*/
                      insertarUsuario(
                          dni = dni,
                          clave = clave,
                          nombres = nombres,
                          apellidos =  apellidos,
                          id_rol = "3",
                          contexto = contexto,
                          respuesta = {
                              mensaje = if (it) {
                                "problemas en la carga"
                            } else
                                "se cargaron los datos"
                              dni = ""
                              clave = "123456"
                              nombres = ""
                              apellidos =  ""
                          }
                      )

            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Agregar")
        }
        Text(text = mensaje)
    }
}
