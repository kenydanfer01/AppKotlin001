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
        composable(route = AppPantallas.PantallaPrincipal.route){
            PantallaPrincipal(navController)
        }
        composable(route = AppPantallas.Pantallalogin.route){
            PantallaLogin(navController)
        }
        composable(route = AppPantallas.PrimeraPantalla.route){
            PrimeraPantalla(navController)
        }
        composable(route = AppPantallas.SegundaPantalla.route + "/{text}",
            arguments = listOf(navArgument(name = "text"){
                type = NavType.StringType
            })){
            SegundaPantalla(navController, it.arguments?.getString("text"))
        }
        composable(route = AppPantallas.LoginPantalla.route){
            LoginPantalla(navController)
        }
    }
}