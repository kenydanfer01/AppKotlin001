package com.example.trabajofinal003.pantallas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.trabajofinal003.AccesoDatos.ObtenerListaUsuarios
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.listaUsuarios

@Preview
@Composable
fun Prueba002(){
    Scaffold(modifier = Modifier.fillMaxSize()) {
        MyUI()
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyUI() {
    val contexto = LocalContext.current
    val contextForToast = LocalContext.current.applicationContext

    ObtenerListaUsuarios("2", contexto)

    var selectedItem by remember { mutableStateOf("Elija un Docente") }
    var expanded by remember { mutableStateOf(false) }
    // the box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Elija a un docente") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listaUsuarios.forEach { docente ->
                // menu item
                DropdownMenuItem(onClick = {
                    selectedItem = "${docente.dni} - ${docente.apellidos} + ${docente.nombres}"
                    Toast.makeText(contextForToast, docente.dni, Toast.LENGTH_SHORT).show()
                    expanded = false
                }) {
                    Text(text = "${docente.dni} - ${docente.apellidos} + ${docente.nombres}")
                }
            }
        }
    }
}
