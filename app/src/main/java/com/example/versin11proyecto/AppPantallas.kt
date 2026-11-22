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
    val repositorio = remember { JuegoRepository() }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "lista"
    ) {

        composable("lista") {
            var mostrarCrear by remember { mutableStateOf(false) }

            PantallaPrincipal(
                juegos = repositorio.juegos,
                onJuegoClick = { id -> navController.navigate("detalle/$id") },
                onAgregar = { mostrarCrear = true }
            )

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

        composable(
            route = "detalle/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { entrada ->
            val id = entrada.arguments?.getInt("id")
            val juego = id?.let { repositorio.obtenerPorId(it) }

            var mostrarEditar by remember { mutableStateOf(false) }
            var mostrarBorrar by remember { mutableStateOf(false) }

            if (juego == null) {
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

                if (mostrarBorrar) {
                    DialogoBorrar(
                        nombreJuego = juego.titulo,
                        onConfirmar = {
                            mostrarBorrar = false
                            navController.popBackStack()
                            repositorio.eliminar(juego.id)
                        },
                        onCancelar = { mostrarBorrar = false }
                    )
                }
            }
        }
    }
}