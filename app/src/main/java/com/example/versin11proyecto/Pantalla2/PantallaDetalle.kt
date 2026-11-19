package com.example.versin11proyecto.pantalla2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.versin11proyecto.modelo.Juego

@Composable
fun PantallaDetalle(
    juego: Juego,
    onVolver: () -> Unit,        // Volver a la lista
    onEditar: () -> Unit,        // Abrir el diálogo de edición
    onBorrar: () -> Unit,        // Abrir el diálogo de confirmación de borrado
    modifier: Modifier = Modifier
)   {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()    // Establecer espacio para la barra de estado
            .navigationBarsPadding()        // Establecer espacio para la barra inferior
            .verticalScroll(rememberScrollState()) // Hacer scroll vertical
            .padding(16.dp)
    ) {
        // Cabecera: flecha para volver + título de la pantalla
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onVolver) {
                Text("←", fontSize = 24.sp)
            }
            Text(
                "Detalles Juegos",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Portada + título y fecha
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.LightGray)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    juego.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Lanzamiento: ${juego.fechaLanzamiento}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // Acciones: editar y eliminar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onEditar,
                modifier = Modifier.weight(1f)
            ) {
                Text("Editar")
            }
            OutlinedButton(
                onClick = onBorrar,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = MaterialTheme.colorScheme.error      // Mirar
                )
            ) {
                Text("Eliminar")
            }
        }

        // Estado
        TituloSeccion("Estado")
        Text(juego.estado)

        // Tráiler (marcador de posición del prototipo)
        TituloSeccion("Tráiler")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(Color.LightGray)
        )

        // Fotos (4 cuadros, 2 por fila, con weight para que tengan ancho)
        TituloSeccion("Fotos")
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            repeat(2) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)
                                .background(Color.LightGray)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

// Título de cada bloque, para no repetir el mismo Text varias veces
@Composable
private fun TituloSeccion(texto: String) {
    Text(
        texto,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 20.dp, bottom = 8.dp)
    )
}