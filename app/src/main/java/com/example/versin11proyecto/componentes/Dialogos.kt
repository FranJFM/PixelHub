package com.example.versin11proyecto.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.versin11proyecto.modelo.Juego


private val ESTADOS = listOf("Pendiente", "Jugando", "Terminado")

// Comprueba el formato dd/mm/aaaa
private fun fechaValida(texto: String): Boolean {
    val partes = texto.split("/")           //buscada en internet
    if (partes.size != 3) return false
    val dia = partes[0].toIntOrNull() ?: return false
    val mes = partes[1].toIntOrNull() ?: return false
    val anio = partes[2].toIntOrNull() ?: return false
    return dia in 1..31 && mes in 1..12 && partes[2].length == 4 && anio > 0
}


/**
 * Un único diálogo para crear y editar.
 * - Crear: juegoInicial = null (campos vacíos).
 * - Editar: juegoInicial = el juego (campos rellenos).
 */
@Composable
fun DialogoJuego(
    titulo: String,
    juegoInicial: Juego?,
    onGuardar: (titulo: String,creador:String, fecha: String, estado: String) -> Unit,
    onCancelar: () -> Unit
) {
    var nombre by remember { mutableStateOf(juegoInicial?.titulo ?: "") }
    var creador by remember { mutableStateOf(juegoInicial?.creador ?: "") }
    var fecha by remember { mutableStateOf(juegoInicial?.fechaLanzamiento ?: "") }
    var estado by remember { mutableStateOf(juegoInicial?.estado ?: ESTADOS[0]) }


    // Los errores solo se enseñan después de pulsar Guardar
    var intentoGuardar by remember { mutableStateOf(false) }

    val errorNombre = if (nombre.isBlank()) "El título es obligatorio" else null
    val errorFecha = when {
        fecha.isBlank() -> "La fecha es obligatoria"            //buscado en internet
        !fechaValida(fecha.trim()) -> "Usa el formato dd/mm/aaaa"
        else -> null
    }

    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text(titulo) },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Título") },
                    singleLine = true,
                    isError = intentoGuardar && errorNombre != null,
                    supportingText = {
                        if (intentoGuardar && errorNombre != null) Text(errorNombre)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = creador,
                    onValueChange = { creador = it },
                    label = { Text("Creador") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = fecha,
                    onValueChange = { fecha = it },
                    label = { Text("Lanzamiento (dd/mm/aaaa)") },
                    singleLine = true,
                    isError = intentoGuardar && errorFecha != null,
                    supportingText = {
                        if (intentoGuardar && errorFecha != null) Text(errorFecha)
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Estado", style = MaterialTheme.typography.labelLarge)
                ESTADOS.forEach { opcion ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = estado == opcion,
                                onClick = { estado = opcion },
                                role = Role.RadioButton
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(selected = estado == opcion, onClick = null)
                        Text(opcion, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                intentoGuardar = true
                // Solo guardamos si no hay ningún error
                if (errorNombre == null && errorFecha == null) {
                    onGuardar(nombre.trim(),creador.trim() ,fecha.trim(), estado)
                }
            }) {
                Text("Guardar")
            }
        },
        dismissButton = { // Botón para cancelar
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}

/** Diálogo de confirmación antes de eliminar. */
@Composable
fun DialogoBorrar(
    nombreJuego: String,
    onConfirmar: () -> Unit,
    onCancelar: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onCancelar,
        title = { Text("Eliminar juego") },
        text = { Text("¿Seguro que quieres eliminar \"$nombreJuego\"?") },
        confirmButton = {
            TextButton(onClick = onConfirmar) {
                Text("Eliminar", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onCancelar) {
                Text("Cancelar")
            }
        }
    )
}