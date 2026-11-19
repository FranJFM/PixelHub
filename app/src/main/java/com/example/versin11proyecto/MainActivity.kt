package com.example.versin11proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.versin11proyecto.componentes.BarraInferior
import com.example.versin11proyecto.pantalla1.explorarPantalla
import com.example.versin11proyecto.ui.theme.Versión11ProyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Versión11ProyectoTheme {
                explorarPantalla()
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun Preview() {
    Versión11ProyectoTheme {
        explorarPantalla()
    }
}