package com.example.trabajofinal003.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajofinal003.R

@Composable
fun PantallaInfoColegio(navController: NavController){
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
                Text(text = "SOBRE NOSOTROS: ")
            }
        }
    ) {
        BodyPantallaInfoColegio(navController)
    }
}

@Composable
fun BodyPantallaInfoColegio(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) { item {
            Spacer(modifier = Modifier.size(5.dp))
            TextoV01(texto = "SAN MIGUEL DE PIURA", size_sp = 40)
            Spacer(modifier = Modifier.size(5.dp))
            ImagenPrincipal(R.drawable.frontis_sm)
        Text(text = "El Colegio San Miguel de Piura es el colegio más antiguo de la ciudad de Piura. " +
                "Fue fundado el 29 de septiembre de 1835 por la Congregación de Hermanos Franciscanos. " +
                "En la actualidad imparte educación en los niveles primaria y secundaria en tres turnos. " +
                "Está ubicado en el centro de la ciudad, en el barrio de Buenos Aires.",
        modifier = Modifier.padding(8.dp))
        TextoV01(texto = "HISTORIA", size_sp = 30)
        ImagenPrincipal(id = R.drawable.historia002)
        Text(text = "San Miguel es una reliquia para Piura, figura entre los colegios más antiguos del Perú y su existencia data de 1835. " +
                "Conoce de todo, de épocas brillantes y de tiempos azarosos. Esta es parte de su historia.\n" +
                "\n" +
                "El colegio fue fundado el 29 de septiembre de 1835, siendo su primer director Don José de Lama, un adinerado personaje piurano, " +
                "quien también fue alcalde de la ciudad.\n" +
                "\n" +
                "Comenzó a funcionar en un inmueble construido a fines del siglo XVIII, vuelto a levantar después del terremoto de 1912. " +
                "Aquí el colegio funcionó hasta 1953, año en que el local comenzó a ser utilizado para albergar al colegio “Nuestra Señora de Fátima”. " +
                "En la actualidad ésta casona cobija al Centro Cultural del Instituto Nacional de Cultura.\n" +
                "\n" +
                "Retornando a la reseña de sus primeros años, después de una época muy convulsionada, de crisis y desorden en el país, hubo un cierre obligado " +
                "del colegio, éste se reapertura en 1845. Según el historiador Jorge Basadre, desde 1845 hasta 1851, los principales planteles del país " +
                "disfrutaron de “una época de renacimiento y progreso” y entre ellos menciona al de Piura, llamado a partir de 1856 " +
                "“Colegio de Ciencias de San Miguel de Piura”.",
            modifier = Modifier.padding(8.dp))
        ImagenPrincipal(id = R.drawable.historia)
        }
    }
}