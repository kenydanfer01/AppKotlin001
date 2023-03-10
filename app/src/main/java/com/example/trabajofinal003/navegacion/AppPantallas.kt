package com.example.trabajofinal003.navegacion

sealed class AppPantallas(val route: String){

    /* PANTALLAS GENERALES: */
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object PantallaLogin: AppPantallas("pantalla_login")
    object PantallaInfoColegio: AppPantallas("pantalla_infoColegio")
    object PantallaGestionarCuenta: AppPantallas("pantalla_gestionarCuenta")
    object PantallaVerNotas: AppPantallas("pantalla_verNotas")

    /* PANTALLAS SEGÚN USUARIO: */
    object PantallaDocente: AppPantallas("Pantalla_docente")
    object PantallaAlumno: AppPantallas("pantalla_alumno")
    object PantallaDirector: AppPantallas("Pantalla_director")

    /* PANTALLAS PARA DOCENTES: */
    object PantallaCursoInfo: AppPantallas("pantalla_cursoInfo")
    object PantallaActualizarNotas: AppPantallas("pantalla_actualizarNotas")

    /* PANTALLAS PARA ALUMNOS: */


    /* ---------------------------PANTALLAS PARA EL DIRECTOR:------------------------------------- */
    /* PANTALLAS PARA LISTAR (GESTIONAR) */
    object PantallaGestionarAlumnos: AppPantallas("pantalla_gestionarAlumnos")
    object PantallaGestionarCursos: AppPantallas("pantalla_gestionarCursos")
    object PantallaGestionarDocentes: AppPantallas("pantalla_gestionarDocentes")
    object PantallaEditarCurso: AppPantallas("pantalla_editarCurso")

    /* PANTALLAS PARA REGISTRAR O INSERTAR */
    object PantallaAgregarAlumno: AppPantallas("pantalla_agregarAlumno")
    object PantallaAgregarDocente: AppPantallas("pantalla_agregarDocente")
    object PantallaAgregarCurso: AppPantallas("pantalla_agregarCurso")
    object PantallaAgregarAlumnoCurso: AppPantallas("pantalla_agregarAlumnoCurso")

    /* PANTALLAS AUXILIARES Y DE PRUEBAS: (Luego las borraré)*/
    object PrimeraPantalla: AppPantallas("primera_pantalla")
    object SegundaPantalla: AppPantallas("segunda_pantalla")
    object LoginPantalla: AppPantallas("login")
}
