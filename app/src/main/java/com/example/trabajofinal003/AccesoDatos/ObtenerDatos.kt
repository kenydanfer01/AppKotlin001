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

const val base_route = "http://192.168.1.6/SanMiguel/"

/* FUNCIÓN PARA EL LOGIN:
 1) Declaro un Data Clas UsuarioId con us atributos id, id_rol, me servirá para cuando haga el login sepa
 el rol y saber a que pantalla enviarlo el id, para obtener los nombres y datos del uduario  */
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

/* FUNCIÓN PARA OBTENER INFORMACIÓN DEL USUARIO
 1) Declaro el data class Usuario con sus atributos (id, id_rol, dni, nombres, apellidos), los cuales me
 servirán para en la vista mostrar esos datos, ya que en la respuesta de la función envío un objeto de DataClass Usuario */
data class Usuario(val id: String?, val id_rol: String, val dni: String, val clave: String, val nombres: String, val apellidos: String)
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
                        objeto.getString("clave"),
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

/* PARA OBTENER LA INFORMACIÓN DE UN CURSO, con info del Docente a cargo y la cantidad de alumnos */
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

/* ----------------------- OBTENER LAS NOTAS DE UN ALUMNO EN UN CURSO ----------------------------- */
data class NotasCurso(val id: String?, val id_curso: String, val id_alumno: String,
                      val n1: String, val n2: String, val n3: String, val n4: String, val prom: String)
fun ObtenerNotas(id_registro: String?, contexto: Context, respuesta: (NotasCurso?) -> Unit){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "getNotasAlumno.php?id_registro=$id_registro"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    val objeto = JSONObject(response[0].toString())
                    val notasCurso = NotasCurso(
                        objeto.getString("id"),
                        objeto.getString("id_curso"),
                        objeto.getString("id_alumno"),
                        objeto.getString("nota1"),
                        objeto.getString("nota2"),
                        objeto.getString("nota3"),
                        objeto.getString("nota4"),
                        objeto.getString("promedio"),
                    )
                    respuesta(notasCurso)
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

