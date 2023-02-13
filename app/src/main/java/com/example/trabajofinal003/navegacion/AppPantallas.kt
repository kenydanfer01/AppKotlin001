package com.example.trabajofinal003.navegacion

sealed class AppPantallas(val route: String){
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object PantallaLogin: AppPantallas("pantalla_login")
    object PantallaDocente: AppPantallas("Pantalla_docente")
    object PantallaAlumno: AppPantallas("pantalla_alumno")
    object PantallaCursoInfo: AppPantallas("pantalla_cursoInfo")
    object PrimeraPantalla: AppPantallas("primera_pantalla")
    object SegundaPantalla: AppPantallas("segunda_pantalla")
    object LoginPantalla: AppPantallas("login")
//    object Login02: AppPantallas("login02")
}
