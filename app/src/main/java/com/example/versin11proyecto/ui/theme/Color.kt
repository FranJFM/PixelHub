package com.example.versin11proyecto.ui.theme

import androidx.compose.ui.graphics.Color

val FondoPixel = Color(0xFF0F0B1F)
val SuperficiePixel = Color(0xFF1A1433)
val TarjetaPixel = Color(0xFF261E4A)


val CianNeon = Color(0xFF00E5FF)
val MagentaNeon = Color(0xFFFF4FD8)
val OroEstrella = Color(0xFFFFD84D)
val VerdeTerminado = Color(0xFF5DFFA0)

val TextoPrincipal = Color(0xFFEDE7FF)
val TextoSecundario = Color(0xFFB9B0D9)
val BordePixel = Color(0xFF5B4FA0)
val ErrorPixel = Color(0xFFFF6B81)

fun colorDeEstado(estado: String): Color = when (estado) {
    "Terminado" -> VerdeTerminado
    "Jugando" -> CianNeon
    else -> OroEstrella
}