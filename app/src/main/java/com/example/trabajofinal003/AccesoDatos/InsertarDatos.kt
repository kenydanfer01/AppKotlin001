package com.example.trabajofinal003.AccesoDatos

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

/* FUNCIÓN PARA INSERTAR UN NUEVO USUARIO (Docente, Alumno o Director) */
fun insertarUsuario(dni: String, clave: String, nombres: String, apellidos: String, id_rol: String,
                    contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "setUsuario.php"
    val parametros= JSONObject()
    parametros.put("dni", dni)
    parametros.put("clave", clave)
    parametros.put("nombres", nombres)
    parametros.put("apellidos", apellidos)
    parametros.put("id_rol", id_rol)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->
            if (response.get("respuesta").toString() == "ok")
                respuesta(true)
            else
                respuesta(false)
        },
        {
            respuesta(false)
        }
    )
    requestQueue.add(requerimiento)
}

/* ---------------  FUNCIÓN PARA INSERTAR UN NUEVO CURSO (nombreCurso, id_docente) ------------- */
fun insertarCurso(nombreCurso: String, id_docente: String, contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "setCurso.php"
    val parametros= JSONObject()
    parametros.put("nombreCurso", nombreCurso)
    parametros.put("id_docente", id_docente)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->
            if (response.get("respuesta").toString() == "ok")
                respuesta(true)
            else
                respuesta(false)
        },
        {
            respuesta(false)
        }
    )
    requestQueue.add(requerimiento)
}

/* ---------------------- FUNCIÓN PARA INSERTAR UN ALUMNO EN UN CURSO -------------------------- */
fun insertarAlumnoCurso(id_curso: String?, id_alumno: String, contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "setAlumnoCurso.php"
    val parametros= JSONObject()
    parametros.put("id_curso", id_curso)
    parametros.put("id_alumno", id_alumno)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->
            if (response.get("respuesta").toString() == "ok")
                respuesta(true)
            else
                respuesta(false)
        },
        {
            respuesta(false)
        }
    )
    requestQueue.add(requerimiento)
}
