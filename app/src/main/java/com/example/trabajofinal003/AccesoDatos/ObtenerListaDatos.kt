package com.example.trabajofinal003.AccesoDatos

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

/* -------PARA OBTENER LA LISTA DE TODOS USUARIOS EN GENERAL (POR FILTRO SEGÚN SU ROL "id_rol"----- )
1) Declaro un Data Class UsuarioInfo, con sus atributos (id, dni, apellidos, nombres)
2) Declaro un val Arreglo MutableState (que cambiará de estado) al agregarle Usuario (objetos de la data class UsuarioInfo)
3) Empieza la Función ObtenerListaUsuarios y recibe el parámetro de id_rol y el de tipo Context
4) siempre me retorna la lista de Usuarios, segun el contexto en que se encuentre */
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

/* ------------ PARA OBTENER LA LISTA DE LOS ALUMNOS QUE PERTENECEN A UN CURSO ------------------ */
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

/* -------------- PARA OBTENER LA LISTA DE LOS ALUMNOS QUE NOOO PERTENECEN A UN CURSO ------------ */
fun ObtenerAlumnosSinCurso(id_curso: String?, contexto: Context) {
    val url = base_route + "getAlumnosSinCurso.php?id_curso='$id_curso'"
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
                val dni =  registro.getString("dni")
                val apellidos = registro.getString("apellidos")
                val nombres = registro.getString("nombres")
                listaUsuarios.add(UsuarioInfo(id, dni, apellidos, nombres))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}

/* --------------------- PARA OBTENER LA LISTA DE TODOS CURSOS EN GENERAL --------------------------
1) Declaro un Data Class Curso, con sus atributos (id_curso, nombre)
2) Declaro un val Arreglo MutableState (que cambiará de estado) al agregarle Curso (objetos de la data class Curso)
3) Empieza la Función ObtenerListaCurso y sólo recibe el parámetro de tipo Context */
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

/* --------------------PARA OBTENER LOS CURSOS EN LOS QUE ESTÁ INSCRITO UN ALUMNO ---------------------*/
data class CursoAlumno(val id: String, val id_curso: String, val nombreCurso: String, val id_docente: String,
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
                val id = registro.getString("id")
                val id_curso = registro.getString("id_curso")
                val nombreCurso = registro.getString("nombreCurso")
                val id_docente = registro.getString("id_docente")
                val apeDocente = registro.getString("apeDocente")
                val nomDocente = registro.getString("nomDocente")
                cursosAlumno.add(CursoAlumno(id, id_curso, nombreCurso, id_docente, apeDocente, nomDocente))
            }
        },
        { error ->
        }
    )
    requestQueue.add(jsonObjectRequest)
}

/* ------------------- OBTENER LISTA DE LOS CURSOS QUE TIENE A CARGO UN DOCENTE -------------------- */
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