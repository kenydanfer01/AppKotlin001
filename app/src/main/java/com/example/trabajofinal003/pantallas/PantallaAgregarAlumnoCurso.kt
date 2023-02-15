package com.example.trabajofinal003.pantallas


import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.*
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaAgregarAlumnoCurso(navController: NavController, id_curso: String?){
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
                Text(text = "Nuevo Alumno en Curso")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaAgregarAlumnoCurso(navController, id_curso)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyPantallaAgregarAlumnoCurso(navController: NavController, id_curso: String? ){
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
        TextoV01(texto = "CURSO: $nombreCurso", size_sp = 20)
        TextoV01(texto = "DOCENTE: $nombreDocente $apellidoDocente" , size_sp = 18)
        Spacer(modifier = Modifier.padding(16.dp))
        TextoV01(texto = "SELECCIONE AL NUEVO ALUMNO: " , size_sp = 20)
        Spacer(modifier = Modifier.padding(16.dp))

        var value01 = false
        var mensaje by remember { mutableStateOf("") }
        var id_alumno by remember { mutableStateOf("") }

        ObtenerAlumnosSinCurso(id_curso, contexto)
        var selectedItem by remember { mutableStateOf("Elija un Alumno") }
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
                label = { Text(text = "Elija a un Alumno") },
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
                listaUsuarios.forEach { alumno ->
                    // menu item
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            selectedItem = "${alumno.dni} - ${alumno.apellidos}, ${alumno.nombres}"
                            expanded = false
                            id_alumno = alumno.id
                        }) {
                        Text(text = "${alumno.dni} - ${alumno.apellidos}, ${alumno.nombres}",
                            modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }

        Button(
            onClick = {
                /* Llamamos a la Función insertarAlumnoCurso, y le envíamos los parámetros necesarios*/
                insertarAlumnoCurso(
                    id_curso = id_curso,
                    id_alumno= id_alumno, /* Porque se trata de Alumnos, el id_rol = 3 */
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
                    Toast.makeText(contexto, " SE AGREGÓ AL ALUMNO CORRECTAMENTE !!! ", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Agregar")
        }
        Text(text = mensaje)
    }
}

