package com.schmitttech.ingresso.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Cream,
    onPrimary = Noir,
    secondary = Cream,
    onSecondary = Noir,
    secondaryContainer = Cream,
    onSecondaryContainer = Noir,
    background = Noir,
    onBackground = Cream,
    surface = Noir,
    onSurface = Cream,
    surfaceVariant = DarkGray,
    onSurfaceVariant = Cream,
    error = AccentRed,
    onError = Cream,
    outline = Cream
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = LightGray,
    secondary = White,
    onSecondary = White,
    secondaryContainer = Green,
    onSecondaryContainer = LightGray,
    background = LightGray,
    onBackground = Green,
    surface = LightGray,
    onSurface = Green,
    surfaceVariant = LightGray,
    onSurfaceVariant = Green,
    error = AccentRed,
    onError = LightGray,
    outline = Green
)

@Composable
fun IngressoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}