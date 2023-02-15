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
fun PantallaVerNotas(navController: NavController, id_registro: String?){
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
        BodyPantallaVerNotas(navController, id_registro)
    }
}

@Composable
fun BodyPantallaVerNotas(navController: NavController, id_registro: String?) {
    val contexto = LocalContext.current

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
        TextoV01("En el curso: ${nombreCurso} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Con el docente: ${docente} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("El alumno: ${nombres}, ${apellidos} ", 20)
        Spacer(modifier = Modifier.height(12.dp))
        TextoV01("Tiene las calificaiones: ", 20)
        Column(modifier = Modifier.padding(start = 20.dp)) {
            Text(text = "Nota 1: ${n1}", fontSize = 20.sp)
            Text(text = "Nota 2: ${n2}", fontSize = 20.sp)
            Text(text = "Nota 3: ${n3}", fontSize = 20.sp)
            Text(text = "Nota 4: ${n4}", fontSize = 20.sp)
            Text(text = "Promedio Final: ${prom}", fontSize = 20.sp)
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(10.dp)
        ) { Text(text = "VOLVER ATRÁS")}
    }

}



