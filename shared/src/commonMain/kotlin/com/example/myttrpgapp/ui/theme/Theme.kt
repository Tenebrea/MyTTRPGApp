package com.example.myhabitapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.myttrpgapp.ui.theme.legendInTheMistTypography
import jdk.jshell.Snippet

private val LightColorScheme = lightColorScheme(
    primary = PowerTagColor,
    onPrimary = OnTagColor,

    primaryContainer = OrangeLight,
    onPrimaryContainer = Brown,

    secondary = WeaknessTagColor,
    onSecondary = OnTagColor,

    secondaryContainer = GreenLight,
    onSecondaryContainer = Brown,

    tertiary = StatusColor,
    onTertiary = OnTagColor,

    tertiaryContainer = PinkLight,
    onTertiaryContainer = Brown,

    background = Cream,
    onBackground = Color.Black,

    surface = WarmWhite,
    onSurface = Color(0xFF0C0C0C),

    outline = BrownLight,

    error = Error,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = PowerTagColor,
    onPrimary = OnTagColor,

    primaryContainer = OrangeDark,
    onPrimaryContainer = WarmWhite,

    secondary = WeaknessTagColor,
    onSecondary = OnTagColor,

    secondaryContainer = Brown,
    onSecondaryContainer = GreenLight,

    tertiary = StatusColor,
    onTertiary = OnTagColor,

    tertiaryContainer = Brown,
    onTertiaryContainer = PinkLight,

    background = Color(0xFF2F2A26),
    onBackground = WarmWhite,

    surface = Color(0xFF0C0C0C),
    onSurface = WarmWhite,

    outline = Color(0xFF9D8982),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005)
)

@Composable
fun TTRPGAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        },
        typography = legendInTheMistTypography(),
        shapes = AppShapes,
        content = content
    )
}