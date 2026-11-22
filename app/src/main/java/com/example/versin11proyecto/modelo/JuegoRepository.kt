package com.example.versin11proyecto.modelo

import androidx.compose.runtime.mutableStateListOf
class JuegoRepository {
    val juegos = mutableStateListOf(
        Juego(1, "Hollow Knight","Team Cherry", "19/02/2017", "Terminado"),
        Juego(2, "The Last Of Us","Naughty Dog", "19/06/2013", "Terminado"),
        Juego(3, "Unreturning","Dani", "11/03/2027", "Pendiente")
    )

    private var siguienteId = (juegos.maxOfOrNull { it.id } ?: 0) + 1

    fun obtenerPorId(id: Int): Juego? {
        return juegos.find { it.id == id }
    }

    fun agregar(titulo: String,creador:String ,fechaLanzamiento: String, estado: String) {
        juegos.add(Juego(siguienteId, titulo, creador, fechaLanzamiento, estado))
        siguienteId++
    }


    fun actualizar(juego: Juego) {
        val posicion = juegos.indexOfFirst { it.id == juego.id }
        if (posicion != -1) {
            juegos[posicion] = juego
        }
    }

    fun eliminar(id: Int) {
        juegos.removeAll { it.id == id }
    }
}