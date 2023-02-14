package com.example.trabajofinal003.navegacion

sealed class AppPantallas(val route: String){

    /* PANTALLAS GENERALES: */
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object PantallaLogin: AppPantallas("pantalla_login")
    object PantallaInfoColegio: AppPantallas("pantalla_infoColegio")

    /* PANTALLAS SEGÚN USUARIO: */
    object PantallaDocente: AppPantallas("Pantalla_docente")
    object PantallaAlumno: AppPantallas("pantalla_alumno")
    object PantallaDirector: AppPantallas("Pantalla_director")

    /* PANTALLAS PARA DOCENTES: */
    object PantallaCursoInfo: AppPantallas("pantalla_cursoInfo")

    /* PANTALLAS PARA ALUMNOS: */


    /* PANTALLAS PARA EL DIRECTOR: */
    object PantallaGestionarAlumnos: AppPantallas("pantalla_gestionarAlumnos")
    object PantallaAgregarAlumno: AppPantallas("pantalla_agregarAlumno")

    /* PANTALLAS AUXILIARES Y DE PRUEBAS: (Luego las borraré)*/
    object PrimeraPantalla: AppPantallas("primera_pantalla")
    object SegundaPantalla: AppPantallas("segunda_pantalla")
    object LoginPantalla: AppPantallas("login")
}
