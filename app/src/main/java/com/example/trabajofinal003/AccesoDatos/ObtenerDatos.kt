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

/* ---------------------- FUNCIONES PARA OBTENER DATOS (SIN PARÁMETROS)--------------------------- */

/* PARA OBTENER LA LISTA DE TODOS USUARIOS EN GENERAL (POR FILTRO SEGÚN SU ROL "id_rol" )
* 1) Declaro un Data Class UsuarioInfo, con sus atributos (id, dni, apellidos, nombres)
* 2) Declaro un val Arreglo MutableState (que cambiará de estado) al agregarle Usuario (objetos de la data class UsuarioInfo)
* 3) Empieza la Función ObtenerListaUsuarios y recibe el parámetro de id_rol y el de tipo Context
* 4) siempre me retorna la lista de Usuarios, segun el contexto en que se encuentre */
data class UsuarioInfo(val id: String, val dni: String, val apellidos: String, val nombres: String)
val listaUsuarios = mutableStateListOf<UsuarioInfo>()
fun ObtenerListaUsuarios(id_rol: String, contexto: Context) {
    val url = base_route + "getListaUsuarios.php?id_rol='$id_rol'"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            listaUsuarios.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id = registro.getString("id")
                val dni = registro.getString("dni")
                val apellidos = registro.getString("apellidos")
                val nombres = registro.getString("nombres")
                listaUsuarios.add( UsuarioInfo(id, dni, apellidos, nombres) )
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}


/* PARA OBTENER LA LISTA DE TODOS CURSOS EN GENERAL
* 1) Declaro un Data Class Curso, con sus atributos (id_curso, nombre)
* 2) Declaro un val Arreglo MutableState (que cambiará de estado) al agregarle Curso (objetos de la data class Curso)
* 3) Empieza la Función ObtenerListaCurso y sólo recibe el parámetro de tipo Context */
data class Curso(val id_curso: String, val nombre: String)
val listaCursos = mutableStateListOf<Curso>()
fun ObtenerListaCursos(contexto: Context) {
    val url = base_route + "getListaCursos.php"
    val requestQueue = Volley.newRequestQueue(contexto)
    val jsonObjectRequest = JsonObjectRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            val jsonArray = response.getJSONArray("lista")
            listaCursos.clear()
            for (i in 0 until jsonArray.length()) {
                val registro = jsonArray.getJSONObject(i)
                val id_curso = registro.getString("id")
                val nombre = registro.getString("nombre")
                val add = listaCursos.add(Curso(id_curso, nombre))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
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


/* -------------------------- FUNCIONES PARA INSERTAR DATOS ------------------------------------- */
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









