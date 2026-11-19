package com.example.versin11proyecto

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.versin11proyecto.componentes.DialogoBorrar
import com.example.versin11proyecto.componentes.DialogoJuego
import com.example.versin11proyecto.modelo.JuegoRepository
import com.example.versin11proyecto.pantalla1.PantallaPrincipal
import com.example.versin11proyecto.pantalla2.PantallaDetalle

@Composable
fun AppPantallas() {
    // Repositorio en memoria: aquí viven los datos
    val repositorio = remember { JuegoRepository() }

    // El NavController gestiona la pila de pantallas y el botón Atrás
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista"
    ) {

        // ---------- Pantalla 1: lista ----------
        composable("lista") {
            var mostrarCrear by remember { mutableStateOf(false) }

            PantallaPrincipal(
                juegos = repositorio.juegos,
                onJuegoClick = { id -> navController.navigate("detalle/$id") },
                onAgregar = { mostrarCrear = true }
            )

            // Diálogo de creación
            if (mostrarCrear) {
                DialogoJuego(
                    titulo = "Añadir juego",
                    juegoInicial = null,
                    onGuardar = { titulo, creador,fecha, estado ->
                        repositorio.agregar(titulo,creador, fecha, estado)
                        mostrarCrear = false
                    },
                    onCancelar = { mostrarCrear = false }
                )
            }
        }

        // ---------- Pantalla 2: detalle (recibe el id en la ruta) ----------
        composable(
            route = "detalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entrada ->
            val id = entrada.arguments?.getInt("id")
            val juego = id?.let { repositorio.obtenerPorId(it) }

            var mostrarEditar by remember { mutableStateOf(false) }
            var mostrarBorrar by remember { mutableStateOf(false) }

            if (juego == null) {
                // Si el juego ya no existe (p. ej. tras girar el móvil y reiniciarse
                // el repositorio), volvemos a la lista. La comprobación evita cerrar
                // la pila cuando ya estamos en la lista.
                LaunchedEffect(Unit) {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            } else {
                PantallaDetalle(
                    juego = juego,
                    onVolver = { navController.popBackStack() },
                    onEditar = { mostrarEditar = true },
                    onBorrar = { mostrarBorrar = true }
                )

                // Diálogo de edición
                if (mostrarEditar) {
                    DialogoJuego(
                        titulo = "Editar juego",
                        juegoInicial = juego,
                        onGuardar = { titulo, creador,fecha, estado ->
                            repositorio.actualizar(
                                juego.copy(
                                    titulo = titulo,
                                    creador = creador,
                                    fechaLanzamiento = fecha,
                                    estado = estado
                                )
                            )
                            mostrarEditar = false
                        },
                        onCancelar = { mostrarEditar = false }
                    )
                }

                // Diálogo de confirmación de borrado
                if (mostrarBorrar) {
                    DialogoBorrar(
                        nombreJuego = juego.titulo,
                        onConfirmar = {
                            mostrarBorrar = false
                            navController.popBackStack()      // vuelve a la lista
                            repositorio.eliminar(juego.id)    // y se elimina el juego
                        },
                        onCancelar = { mostrarBorrar = false }
                    )
                }
            }
        }
    }
}