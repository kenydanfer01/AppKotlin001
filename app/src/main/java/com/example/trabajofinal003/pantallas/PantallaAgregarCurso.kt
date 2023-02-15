package com.example.trabajofinal003.pantallas

import android.widget.Toast
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.ObtenerListaUsuarios
import com.example.trabajofinal003.AccesoDatos.insertarCurso
import com.example.trabajofinal003.AccesoDatos.insertarUsuario
import com.example.trabajofinal003.AccesoDatos.listaUsuarios
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaAgregarCurso(navController: NavController){
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
                Text(text = "Registrar Nuevo Curso")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaAgregarCurso(navController)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyPantallaAgregarCurso(navController: NavController){
    val contexto = LocalContext.current
    val contextForToast = LocalContext.current.applicationContext
    var value01 = false
    Column(
        modifier = Modifier.fillMaxSize() ,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        var nombreCurso by remember { mutableStateOf("") }
        var id_docente by remember { mutableStateOf("") }
        var mensaje by remember { mutableStateOf("") }
        OutlinedTextField(
            value = nombreCurso,
            onValueChange = { nombreCurso = it },
            label = { Text("NOMBRE") },
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            singleLine = true
        )
        /* le pasamos el 2 en el rol, porque es lista de docentes */
        ObtenerListaUsuarios("2", contexto)
        var selectedItem by remember { mutableStateOf("Elija un Docente") }
        var expanded by remember { mutableStateOf(false) }
        // the box
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedItem,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = "Elija a un docente") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon( expanded = expanded ) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.fillMaxWidth()
            )
            // menu
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                listaUsuarios.forEach { docente ->
                    // menu item
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                        selectedItem = "${docente.dni} - ${docente.apellidos}, ${docente.nombres}"
                        expanded = false
                        id_docente = docente.id
                    }) {
                        Text(text = "${docente.dni} - ${docente.apellidos}, ${docente.nombres}",
                            modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

        Button(
            onClick = {
                /* Llamamos a la Función insertarCurso, y le envíamos los parámetros necesarios*/
                insertarCurso(
                    nombreCurso = nombreCurso,
                    id_docente = id_docente, /* Porque se trata de Alumnos, el id_rol = 3 */
                    contexto = contexto,
                    respuesta = {
                        mensaje = if (it) {
                            "problemas en la carga"
                        } else
                            "se cargaron los datos"
                        value01 = true //Le pongo a true para que se muestre el toast o notificación
                    }
                )
                navController.popBackStack()
                /* Toast o mensaje que se muestra en la parte baja de la pantalla */
                if (true.also { value01 = it }) {
                    Toast.makeText(contexto, "LOS DATOS PUERON MODIFICADOS EXITOSAMENTE !!! ", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Agregar")
        }
        Text(text = mensaje)
    }
}

