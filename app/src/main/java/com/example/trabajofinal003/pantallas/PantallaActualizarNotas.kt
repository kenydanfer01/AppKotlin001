package com.example.trabajofinal003.pantallas


import android.widget.Toast
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.*
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaActualizarNotas(navController: NavController, id_registro: String?){
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
                Text(text = " REGISTRO DE NOTAS ")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaActualizarNotas(navController, id_registro)
    }
}

@Composable
fun BodyPantallaActualizarNotas(navController: NavController, id_registro: String?) {
    val contexto = LocalContext.current
    var value01 = false
    var x: Double
    var mensaje by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var idRegistoAux by remember { mutableStateOf("") }
        if (id_registro != null) {
            idRegistoAux = id_registro
        }
        var n1 by remember { mutableStateOf("") }
        var n2 by remember { mutableStateOf("") }
        var n3 by remember { mutableStateOf("") }
        var n4 by remember { mutableStateOf("") }
        var prom by remember { mutableStateOf("") }
        var id_alumno by remember { mutableStateOf("") }
        var id_curso by remember { mutableStateOf("") }
        LaunchedEffect(key1 = true) {
            ObtenerNotas(id_registro, contexto, respuesta = {
                if (it != null) {
                    id_alumno = it.id_alumno
                    id_curso = it.id_curso
                    n1 = it.n1
                    n2 = it.n2
                    n3 = it.n3
                    n4 = it.n4
                    prom = it.prom
                }
            })
        }
        var nombres by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        ObtenerUsuario(id_usuario = id_alumno, respuesta = {
            if (it != null) {
                nombres = it.nombres
                apellidos = it.apellidos
            } }, contexto = contexto)
        var nombreCurso by remember { mutableStateOf("") }
        var docente by remember { mutableStateOf("") }
        ObtenerInfoCurso(id_curso, respuesta = {
            if (it != null) {
                nombreCurso = it.nombreCurso
                docente = it.nomDocente + ", " + it.apeDocente
            }
        }, contexto)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Curso: ${nombreCurso} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Docente: ${docente} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Alumno: ${nombres}, ${apellidos} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Tiene las calificaiones: ", 20)

        Column(modifier = Modifier.fillMaxSize()) {
                OutlinedTextField(
                    value = n1,
                    onValueChange = { n1 = it },
                    label = { Text("NOTA 1") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = n2,
                    onValueChange = { n2 = it },
                    label = { Text("NOTA 2") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = n3,
                    onValueChange = { n3 = it },
                    label = { Text("NOTA 3") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                OutlinedTextField(
                    value = n4,
                    onValueChange = { n4 = it },
                    label = { Text("NOTA 4") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            OutlinedTextField(
                value = prom,
                onValueChange = { prom = it },
                label = { Text("NOTA 1") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                singleLine = true, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(
                onClick = {
                    val listNotas = listOf(n1.toInt() , n2.toInt() , n3.toInt() , n4.toInt())
                    prom = listNotas.average().toString()
                },
                modifier = Modifier.padding(10.dp)
            ) { Text(text = "OBTENER PROMEDIO")}
            Button(
                onClick = {
                    /* Llamamos a la Función actualizarUsuario, y le envíamos el objeto de la Clase Usuario,
                    En donde sus atributos tendrán los valores de las variables */
                    actualizarNotas(
                        NotasCurso( id_registro, id_curso, id_alumno, n1, n2, n3, n4, prom ),
                        contexto,
                        respuesta = {
                            if (it) {
                                value01 = true //Le pongo a true para que se muestre el toast o notificación
                            } else{
                                mensaje = "ERROR EN LA ACTUALIZACIÓN"
                            }
                        })
                    navController.popBackStack()
                    /* Toast o mensaje que se muestra en la parte baja de la pantalla */
                    if (true.also { value01 = it }) {
                        Toast.makeText(contexto, "NOTAS ACTUALIZADAS EXITOSAMENTE !!! ", Toast.LENGTH_SHORT).show()
                    }
                    //
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "ACTUALIZAR")
            }
        }

        Text(text = mensaje)
    }

}



