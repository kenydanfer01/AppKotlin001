package com.example.trabajofinal003.AccesoDatos

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


/* ------------------- FUNCIÓN PARA ACTUALIZAR LOS DATOS DE UN USUARIO ------------------------- */
fun actualizarUsuario(usuario: Usuario, contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "updateUsuario.php"
    val parametros = JSONObject()
    parametros.put("dni", usuario.dni)
    parametros.put("clave", usuario.clave)
    parametros.put("nombres", usuario.nombres)
    parametros.put("apellidos", usuario.apellidos)
    parametros.put("id", usuario.id)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->
            try {
                val resu = response["resultado"].toString()
                if (resu == "1")
                    respuesta(true)
                else
                    respuesta(false)
            } catch (e: JSONException) {
                respuesta(false)
            }
        }
    ) { respuesta(false) }
    requestQueue.add(requerimiento)
}

/* --------------------- FUNCIÓN PARA ACTUALIZAR LAS NOTAS DEL ALUMNO ---------------------------- */
fun actualizarNotas(notasCurso: NotasCurso, contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "updateNotasAlumno.php"
    val parametros = JSONObject()
    parametros.put("n1", notasCurso.n1)
    parametros.put("n2", notasCurso.n2)
    parametros.put("n3", notasCurso.n3)
    parametros.put("n4", notasCurso.n4)
    parametros.put("prom", notasCurso.prom)
    parametros.put("id", notasCurso.id)
    val requerimiento = JsonObjectRequest(
        Request.Method.POST,
        url,
        parametros,
        { response ->
            try {
                val resu = response["resultado"].toString()
                if (resu == "1")
                    respuesta(true)
                else
                    respuesta(false)
            } catch (e: JSONException) {
                respuesta(false)
            }
        }
    ) { respuesta(false) }
    requestQueue.add(requerimiento)
}