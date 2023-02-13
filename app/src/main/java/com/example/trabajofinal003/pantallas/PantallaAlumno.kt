package com.example.trabajofinal003.pantallas

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.base_route
import com.example.trabajofinal003.navegacion.AppPantallas
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.ui.unit.sp
import com.example.trabajofinal003.AccesoDatos.cursosProfesor

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
            TextoV01(texto = "Bienvenido Alumno:", size_sp = 36)
            TextoV01(texto = nombres, size_sp = 30)
            TextoV01(texto = apellidos, size_sp = 30)
        ObtenerCursosAlumno(id_usuario = id_usuario, contexto = contexto)
        Spacer(modifier = Modifier.padding(16.dp))
        TextoV01(texto = "Usted está inscrito en los Siguientes Cursos: ", size_sp = 30)
        LazyColumn(){
            items(cursosAlumno) { cursoA ->
                Card(
                    elevation = 5.dp,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clickable{
                            //
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

data class CursoAlumno(val id_curso: String, val nombreCurso: String, val id_docente: String,
                        val apeDocente: String, val nomDocente: String)

val cursosAlumno = mutableStateListOf<CursoAlumno>()

fun ObtenerCursosAlumno(id_usuario: String?, contexto: Context) {
    val url = base_route + "getCursosAlumno.php?id_usuario='$id_usuario'"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            cursosAlumno.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id_curso = registro.getString("id_curso")
                val nombreCurso = registro.getString("nombreCurso")
                val id_docente = registro.getString("id_docente")
                val apeDocente = registro.getString("apeDocente")
                val nomDocente = registro.getString("nomDocente")
                val add = cursosAlumno.add(CursoAlumno(id_curso, nombreCurso, id_docente, apeDocente, nomDocente))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}