package com.dragonslotos.lotoseveryday.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Immutable
data class ColorSheme(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
    val background: Color,
    val borderColor:Color,
    val errorColor: Color
)


val LocalColorSheme = staticCompositionLocalOf {
    ColorSheme(
        primary = Color.Unspecified,
        secondary = Color.Unspecified,
        background = Color.Unspecified,
        tertiary = Color.Unspecified,
        errorColor = Color.Unspecified,
        borderColor = Color.Unspecified
    )
}

val LightTheme =
    ColorSheme(
        primary = aczentLotos,
        secondary = lotosLotos,
        tertiary = specTextLotos,
        background = backgroundLotos,
        errorColor = Color.Red,
        borderColor = borderLotos
    )

val DarkColorScheme = ColorSheme(
    primary = aczentDragon,
    secondary = lotosDragon,
    tertiary = specTextDragon,
    background = backgroundDragon,
    errorColor = Color.Red,
    borderColor = borderDragon
)
@Composable
fun LotosTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors =
        when {
            darkTheme -> DarkColorScheme
            else -> LightTheme
        }
    CompositionLocalProvider(

        LocalColorSheme  provides colors,
        content = content
    )

}
object LotosTheme {
    val colors: ColorSheme
        @Composable
        get() = LocalColorSheme.current
}