package com.example.trabajofinal003.navegacion

sealed class AppPantallas(val route: String){
    object PantallaPrincipal: AppPantallas("pantalla_principal")
    object Pantallalogin: AppPantallas("pantalla_login")
    object PrimeraPantalla: AppPantallas("primera_pantalla")
    object SegundaPantalla: AppPantallas("segunda_pantalla")
    object LoginPantalla: AppPantallas("login")
//    object Login02: AppPantallas("login02")
}
