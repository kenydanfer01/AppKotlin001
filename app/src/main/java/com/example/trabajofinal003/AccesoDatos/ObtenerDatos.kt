package com.example.trabajofinal003.AccesoDatos

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

const val base_route = "http://192.168.1.2/SanMiguel/"
/*Hago esto solo para una prueba de commit */
data class UsuarioID(val id: String, val id_rol: String)

fun ValidarLogin(dni: String, clave: String, respuesta: (UsuarioID?) -> Unit, contexto: Context){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "login.php?dni='$dni'&clave='$clave'"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    val objeto = JSONObject(response[0].toString())
                    val usuarioId = UsuarioID(
                        objeto.getString("id"),
                        objeto.getString("id_rol")
                    )
                    respuesta(usuarioId)
                } catch (_: JSONException) {
                }
            }
            else
                respuesta(null)
        }
    ) {
    }
    requestQueue.add(requerimiento)
}

data class Usuario(val id: String, val id_rol: String, val dni: String, val nombres: String, val apellidos: String)

fun ObtenerUsuario(id_usuario: String?, respuesta: (Usuario?) -> Unit, contexto: Context){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "getUsuario.php?id_usuario='$id_usuario'"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    val objeto = JSONObject(response[0].toString())
                    val usuario = Usuario(
                        objeto.getString("id"),
                        objeto.getString("id_rol"),
                        objeto.getString("dni"),
                        objeto.getString("nombres"),
                        objeto.getString("apellidos"),
                    )
                    respuesta(usuario)
                } catch (_: JSONException) {
                }
            }
            else
                respuesta(null)
        }
    ) {
    }
    requestQueue.add(requerimiento)
}

data class CursoProfesor(val id: String, val nombre: String, val id_docente: String)

val cursosProfesor = mutableStateListOf<CursoProfesor>()

fun ObtenerCursosProfesor(id_usuario: String?, contexto: Context) {
    val url = base_route + "getCursosProfesor.php?id_usuario='$id_usuario'"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            cursosProfesor.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id = registro.getString("id")
                val nombre = registro.getString("nombre")
                val id_docente = registro.getString("id_docente")
                val add = cursosProfesor.add(CursoProfesor(id, nombre, id_docente))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}


/* PARA OBTENER LA LISTA DE LOS ALUMNOS QUE PERTENECEN A UN CURSO */
data class AlumnoCurso(val id: String, val id_alumno: String, val apellidos: String, val nombres: String)
val alumnosCurso = mutableStateListOf<AlumnoCurso>()
fun ObtenerAlumnosCurso(id_curso: String?, contexto: Context) {
    val url = base_route + "getAlumnosCurso.php?id_curso='$id_curso'"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            alumnosCurso.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id = registro.getString("id")
                val id_alumno = registro.getString("id_alumno")
                val apellidos = registro.getString("apellidos")
                val nombres = registro.getString("nombres")

                val add = alumnosCurso.add(AlumnoCurso(id, id_alumno, apellidos, nombres))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}

/* PARA OBTENER LA LISTA DE TODOS ALUMNOS EN GENERAL */
data class Alumno(val id_alumno: String, val dni: String, val apellidos: String, val nombres: String)
val listaAlumnos = mutableStateListOf<Alumno>()
fun ObtenerListaAlumnos(contexto: Context) {
    val url = base_route + "getListaAlumnos.php"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            listaAlumnos.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id_alumno = registro.getString("id")
                val dni = registro.getString("dni")
                val apellidos = registro.getString("apellidos")
                val nombres = registro.getString("nombres")

                val add = listaAlumnos.add(Alumno(id_alumno, dni, apellidos, nombres))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}

/* PARA OBTENER LA INFORMACIÓN DE UN CURSO, con la info del Docente a cargo y la cantidad de alumnos */
data class InfoCurso(val id_curso: String, val nombreCurso: String, val id_docente: String,
                     val nomDocente: String, val apeDocente: String, val cantidadAlumnos: String)
fun ObtenerInfoCurso(id_curso: String?, respuesta: (InfoCurso?) -> Unit, contexto: Context){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "getInfoCurso.php?id_curso='$id_curso'"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    val objeto = JSONObject(response[0].toString())
                    val curso = InfoCurso(
                        objeto.getString("id_curso"),
                        objeto.getString("nombreCurso"),
                        objeto.getString("id_docente"),
                        objeto.getString("nomDocente"),
                        objeto.getString("apeDocente"),
                        objeto.getString("cantidadAlumnos")
                    )
                    respuesta(curso)
                } catch (_: JSONException) {
                }
            }
            else
                respuesta(null)
        }
    ) {
    }
    requestQueue.add(requerimiento)
}

/* PARA OBTENER LOS CURSOS EN LOS QUE ESTÁ INSCRITO CADA ALUMNO*/
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













