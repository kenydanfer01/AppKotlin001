package com.example.trabajofinal003.pantallas

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.Usuario
import com.example.trabajofinal003.AccesoDatos.actualizarUsuario
import com.example.trabajofinal003.navegacion.AppPantallas

@Composable
fun PantallaGestionarCuenta(navController: NavController, id_usuario: String?){
    Scaffold(
        topBar = {
            TopAppBar() {
                Spacer(modifier = Modifier.width(5.dp))
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Arrow Back",
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = " Actualizar Información ")
                Spacer(modifier = Modifier.width(110.dp))
                Text(text = "Cerrar Sesión", modifier = Modifier.clickable{
                    navController.navigate(route = AppPantallas.PantallaPrincipal.route)
                })
            }
        }
    ) {
        BodyPantallaGestionarCuenta(navController, id_usuario)
    }
}

@Composable
fun BodyPantallaGestionarCuenta(navController: NavController, id_usuario: String?){
    val contexto = LocalContext.current
    var value01 = false
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var dni by remember { mutableStateOf("") }
        var clave by remember { mutableStateOf("123456") }
        var nombres by remember { mutableStateOf("") }
        var apellidos by remember { mutableStateOf("") }
        var id_rol by remember { mutableStateOf( "") }
        var mensaje by remember { mutableStateOf("") }

        LaunchedEffect(key1 = true){
            ObtenerUsuario(id_usuario = id_usuario, respuesta = {
                if (it != null) {
                    dni = it.dni
                    clave = it.clave
                    nombres = it.nombres
                    apellidos = it.apellidos
                    id_rol = it.id_rol
                }
            }, contexto = contexto )
        }
        OutlinedTextField(
            value = dni,
            onValueChange = { dni = it },
            label = { Text("DNI") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = clave,
            onValueChange = { clave = it },
            label = { Text("CLAVE") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(
            value = nombres,
            onValueChange = { nombres = it },
            label = { Text("Nombres") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("apellidos") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            singleLine = true
        )
        Button(
            onClick = {
                /* Llamamos a la Función actualizarUsuario, y le envíamos el objeto de la Clase Usuario,
                En donde sus atributos tendrán los valores de las variables */
                actualizarUsuario(
                    Usuario( id_usuario, id_rol, dni, clave, nombres, apellidos ),
                    contexto,
                    respuesta = {
                        if (it) {
                            mensaje =""
                            value01 = true //Le pongo a true para que se muestre el toast o notificación
                        } else{
                            mensaje = "ERROR EN LA ACTUALIZACIÓN"
                        }
                    })
                navController.popBackStack()
                /* Toast o mensaje que se muestra en la parte baja de la pantalla */
                if (true.also { value01 = it }) {
                    Toast.makeText(contexto, "LOS DATOS PUERON MODIFICADOS EXITOSAMENTE !!! ", Toast.LENGTH_SHORT).show()
                }
                //
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "ACTUALIZAR")
        }
        Text(text = mensaje)
    }
}


