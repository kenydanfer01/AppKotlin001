package com.example.trabajofinal003.AccesoDatos

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject


data class Usuario(val id: String, val id_rol: String, val dni: String, val nombres: String, val apellidos: String)

fun ObtenerUsuario(id_usuario: String?, respuesta: (Usuario?) -> Unit, contexto: Context){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "http://192.168.1.6/SanMiguel/getUsuario.php?id_usuario='$id_usuario'"
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
