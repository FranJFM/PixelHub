package com.example.versin11proyecto.ui.theme


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


private val EsquemaPixel = darkColorScheme(
    primary = CianNeon,
    onPrimary = Color(0xFF00363D),
    primaryContainer = Color(0xFF004F58),
    onPrimaryContainer = Color(0xFFB8F5FF),

    secondary = MagentaNeon,
    onSecondary = Color(0xFF3A0030),
    secondaryContainer = Color(0xFF5A1D4D),
    onSecondaryContainer = Color(0xFFFFD6F4),

    tertiary = OroEstrella,
    onTertiary = Color(0xFF3B2F00),

    background = FondoPixel,
    onBackground = TextoPrincipal,
    surface = FondoPixel,
    onSurface = TextoPrincipal,
    surfaceVariant = TarjetaPixel,
    onSurfaceVariant = TextoSecundario,

    surfaceContainerLowest = FondoPixel,
    surfaceContainerLow = Color(0xFF150F2B),
    surfaceContainer = SuperficiePixel,
    surfaceContainerHigh = Color(0xFF211A40),
    surfaceContainerHighest = TarjetaPixel,

    outline = BordePixel,
    outlineVariant = Color(0xFF3A3170),

    error = ErrorPixel,
    onError = Color(0xFF3A0010)
)

@Composable
fun Versión11ProyectoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = EsquemaPixel,
        typography = Typography
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}