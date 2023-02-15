package com.example.trabajofinal003.pantallas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trabajofinal003.AccesoDatos.ObtenerListaUsuarios
import com.example.trabajofinal003.AccesoDatos.ObtenerUsuario
import com.example.trabajofinal003.AccesoDatos.listaUsuarios
//
//@Preview
//@Composable
//fun DropdownPreview() {
//    Scaffold(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier.fillMaxSize()) {
//            MyUI()
//            Spacer(modifier = Modifier.height(20.dp))
//            MyUI2()
//        }
//    }
//
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun MyUI() {
//    val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
//    var selectedItem by remember { mutableStateOf("") }
//    var expanded by remember { mutableStateOf(false) }
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = {
//            expanded = !expanded
//        }
//    ) {
//        TextField(
//            value = selectedItem,
//            onValueChange = { selectedItem = it },
//            label = { Text(text = "Label") },
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(
//                    expanded = expanded
//                )
//            },
//            colors = ExposedDropdownMenuDefaults.textFieldColors()
//        )
//        // filter options based on text field value
//        val filteringOptions =
//            listItems.filter { it.contains(selectedItem, ignoreCase = true) }
//        if (filteringOptions.isNotEmpty()) {
//            ExposedDropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false }
//            ) {
//                filteringOptions.forEach { selectionOption ->
//                    DropdownMenuItem(
//                        onClick = {
//                            selectedItem = selectionOption
//                            expanded = false
//                        }
//                    ) {
//                        Text(text = selectionOption)
//                    }
//                }
//            }
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun MyUI2() {
//    val contextForToast = LocalContext.current.applicationContext

//    // val listItems = arrayOf("Favorites", "Options", "Settings", "Share")
//
//    var selectedItem by remember {
//        mutableStateOf(listItems[0])
//    }
//
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//
//    // the box
//    ExposedDropdownMenuBox(
//        expanded = expanded,
//        onExpandedChange = {
//            expanded = !expanded
//        }
//    ) {
//
//        // text field
//        TextField(
//            value = selectedItem,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text(text = "Label") },
//            trailingIcon = {
//                ExposedDropdownMenuDefaults.TrailingIcon(
//                    expanded = expanded
//                )
//            },
//            colors = ExposedDropdownMenuDefaults.textFieldColors()
//        )
//
//        // menu
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            listItems.forEach { selectedOption ->
//                // menu item
//                DropdownMenuItem(onClick = {
//                    selectedItem = selectedOption
//                    Toast.makeText(contextForToast, selectedOption, Toast.LENGTH_SHORT).show()
//                    expanded = false
//                }) {
//                    Text(text = selectedOption)
//                }
//            }
//        }
//    }
//}
//@Composable
//fun otherFunction(){
//    Column(modifier = Modifier.padding(16.dp)) {
//        Text("Select an option:")
//        Spacer(modifier = Modifier.height(8.dp))
//        DropdownMenu(
//            expanded = false,
//            onDismissRequest = { },
//            modifier = Modifier.fillMaxWidth()
//        ) {
////            options.forEach { option ->
////                DropdownMenuItem(
////                    onClick = {
////                        selectedOption = option
////                    }
////                ) {
////                    Text(text = option)
////                }
////            }
//        }
////        Text("Selected option: $selectedOption")
//    }
//}