package com.example.trabajofinal003.AccesoDatos

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


/* --------------------- FUNCIÓN PARA ELIMINAR EL REGISTRO DE UN ALUMNO EN UN CURSO -------------------- */
fun eliminarAlumnoCurso(id_curso: String?, id_alumno: String, contexto: Context, respuesta: (Boolean) -> Unit) {
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = base_route + "deleteAlumnoCurso.php"
    val parametros = JSONObject()
    parametros.put("id_curso", id_curso)
    parametros.put("id_alumno", id_alumno)
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