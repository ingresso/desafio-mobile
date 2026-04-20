package com.schmitttech.ingresso

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.schmitttech.ingresso.presentation.navigation.AppNavigation
import com.schmitttech.ingresso.ui.theme.IngressoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IngressoTheme {
                AppNavigation()
            }
        }
    }
}