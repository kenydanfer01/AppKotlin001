@file:Suppress("NAME_SHADOWING")

package com.example.trabajofinal003.pantallas

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.trabajofinal003.R
import com.example.trabajofinal003.navegacion.AppPantallas
import org.json.JSONException
import org.json.JSONObject

@Composable
fun PantallaLogin(navController: NavController){
    Scaffold (topBar = {
        TopAppBar() {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        navController.popBackStack()
                    })
            Text(text = "ACCESO AL SISTEMA DE GESTIÓN ACADÉMICA")
        }
    }
    ){
        BodyLogin(navController)
    }
}

@Composable
fun BodyLogin(navController: NavController){
    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImagenLogin(id = R.drawable.logo_sm)
            TextoPrincipal(texto = "INICIO DE SESIÓN")
            Login(navController)
        }
}
@Composable
fun Login(navController: NavController) {
    val contexto = LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var usuario by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("") }
        var mensaje by remember { mutableStateOf("") }
        var id :String
        var showPopup by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = {
                Text("Usuario")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = {
                Text("Clave PIN")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )
        Button(
            onClick = {
                ValidarLogin(
                    usuario = usuario,
                    clave = clave,
                    respuesta = {
                        if (it!=null) {
                            mensaje = ""
                            id = it.id
                            navController.navigate(route = AppPantallas.PrimeraPantalla.route)
                        } else {
                            mensaje = ""
                            showPopup = true
                        }
                    },
                    contexto = contexto
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 15.dp)
        ) {
            Text(text = "Agregar")
        }
        Text(text = mensaje)
        val onPopupDismissed = { showPopup = false }
        if (showPopup) {
            AlertDialog(
                onDismissRequest = onPopupDismissed,
                text = {
                    Text("Usuario o Contraseña Inválidos")
                },
                confirmButton = {
                    Button(
                        onClick = onPopupDismissed
                    ) {
                        Text(text = "Reintentar")
                    }
                })
        }
    }
}


data class UsuarioID(val id: String)

fun ValidarLogin(usuario: String, clave: String, respuesta: (UsuarioID?) -> Unit, contexto: Context){
    val requestQueue = Volley.newRequestQueue(contexto)
    val url = "http://192.168.1.2/SanMiguel/login.php?usuario='$usuario'&clave='$clave'"
    val requerimiento = JsonArrayRequest(
        Request.Method.GET,
        url,
        null,
        { response ->
            if (response.length() == 1) {
                try {
                    val objeto = JSONObject(response[0].toString())
                    val usuarioId = UsuarioID(
                        objeto.getString("id")
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

@Composable
fun ImagenLogin(id: Int){
    Image(painter = painterResource(id = id),
        contentDescription = "Login" ,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(190.dp)
    )
}
