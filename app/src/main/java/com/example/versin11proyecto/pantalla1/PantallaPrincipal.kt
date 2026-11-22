package com.example.versin11proyecto.pantalla1


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.versin11proyecto.componentes.BarraInferior
import com.example.versin11proyecto.modelo.Juego
import com.example.versin11proyecto.ui.theme.FondoPixel


@Composable
fun PantallaPrincipal(
    juegos: List<Juego>,
    onJuegoClick: (Int) -> Unit,
    onAgregar: () -> Unit,
    modifier: Modifier = Modifier
) {
    var textoBusqueda by remember { mutableStateOf("") }

    val juegosFiltrados = juegos.filter { juego ->
        juego.titulo.contains(textoBusqueda, ignoreCase = true)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF1E1650), FondoPixel)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "PixelHub",
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

            Text(
                "Mis juegos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 16.dp)
            )

            if (juegosFiltrados.isEmpty()) {
                Text(
                    "No hay juegos que mostrar",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }

            LazyColumn(
                modifier = Modifier.padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 180.dp)
            ) {
                items(juegosFiltrados, key = { it.id }) { juego ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onJuegoClick(juego.id) }
                    ) {
                        tarjetaJuego(juego)
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = onAgregar,
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .navigationBarsPadding()
                .padding(end = 16.dp, bottom = 96.dp)
        ) {
            Text("➕")
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            BarraInferior()
        }
    }
}

