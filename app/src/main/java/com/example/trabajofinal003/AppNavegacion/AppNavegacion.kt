package com.example.trabajofinal003.AppNavegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trabajofinal003.navegacion.AppPantallas
import com.example.trabajofinal003.pantallas.*

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppPantallas.PantallaPrincipal.route){

/* PANTALLAS GENERALES: */
        composable(route = AppPantallas.PantallaPrincipal.route){
            PantallaPrincipal(navController)
        }
        composable(route = AppPantallas.PantallaLogin.route){
            PantallaLogin(navController)
        }
        composable(route = AppPantallas.PantallaInfoColegio.route){
            PantallaInfoColegio(navController)
        }

/* PANTALLAS SEGÚN USUARIOS: Podemos ver que reciben parámetros */
        composable(route = AppPantallas.PantallaDocente.route + "/{id_usuario}",
            arguments = listOf(navArgument(name = "id_usuario"){
                type = NavType.StringType
            })){
            PantallaDocente(navController, it.arguments?.getString("id_usuario"))
        }
        composable(route = AppPantallas.PantallaAlumno.route + "/{id_usuario}",
            arguments = listOf(navArgument(name = "id_usuario"){
                type = NavType.StringType
            })){
            PantallaAlumno(navController, it.arguments?.getString("id_usuario"))
        }
        composable(route = AppPantallas.PantallaDirector.route + "/{id_usuario}",
            arguments = listOf(navArgument(name = "id_usuario"){
                type = NavType.StringType
            })){
            PantallaDirector(navController, it.arguments?.getString("id_usuario"))
        }

/* PANTALLAS PARA DOCENTE: */
        composable(route = AppPantallas.PantallaCursoInfo.route + "/{id_curso}",
            arguments = listOf(navArgument(name = "id_curso"){
                type = NavType.StringType
            })){
            PantallaCursoInfo(navController, it.arguments?.getString("id_curso"))
        }


/* PANTALLAS PARA DIRECTOR: */
        composable(route = AppPantallas.PantallaGestionarAlumnos.route) { PantallaGestionarAlumnos(navController) }
        composable(route = AppPantallas.PantallaAgregarAlumno.route) { PantallaAgregarAlumno(navController) }
        composable(route = AppPantallas.PantallaGestionarCursos.route) { PantallaGestionarCursos( navController ) }
        composable(route = AppPantallas.PantallaGestionarDocentes.route) {PantallaGestionarDocentes( navController)}

/* PANTALLAS AUXILIARES Y DE PRUEBAS: (Luego las borraré)*/
        composable(route = AppPantallas.PrimeraPantalla.route){ PrimeraPantalla(navController) }
        composable(route = AppPantallas.SegundaPantalla.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){ type = NavType.StringType })){
            SegundaPantalla(navController, it.arguments?.getString("text")) }
        composable(route = AppPantallas.LoginPantalla.route){ LoginPantalla(navController) }
    }
}