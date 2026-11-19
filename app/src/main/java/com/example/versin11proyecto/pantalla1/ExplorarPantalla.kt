package com.example.versin11proyecto.pantalla1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.versin11proyecto.componentes.BarraInferior
import com.example.versin11proyecto.modelo.Juego

@Composable
fun explorarPantalla(modifier: Modifier = Modifier){
    var textoBusqueda by remember { mutableStateOf("") }

    val juegos = listOf(
        Juego("Hollow Knight", "19/02/2017", "Terminado"),
        Juego("The Last Of Us", "19/06/2013", "Terminado"),
        Juego("Unreturning","11/03/2027","Pendiente")
    )

    val juegosFiltrados = juegos.filter {juego ->
        juego.titulo.contains(textoBusqueda,ignoreCase = true)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Game Library",
                    style = MaterialTheme.typography.titleLarge
                )
                TextButton(onClick = {}) {
                    Text("Ajustes")
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = textoBusqueda,
                    onValueChange = { textoBusqueda = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Buscar") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {}) {
                    Text("Buscar")
                }
            }
            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(juegosFiltrados) { juego ->
                    tarjetaJuego(juego)
                }
            }
        }
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BarraInferior()
        }
    }
}