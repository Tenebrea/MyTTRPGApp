package com.example.myttrpgapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import myttrpgapp.shared.generated.resources.Res
import myttrpgapp.shared.generated.resources.alegreya_bold
import myttrpgapp.shared.generated.resources.alegreya_italic
import myttrpgapp.shared.generated.resources.alegreya_regular
import myttrpgapp.shared.generated.resources.cinzel_decorative_bold
import myttrpgapp.shared.generated.resources.im_fell_english_regular
import org.jetbrains.compose.resources.Font

// 1. Define the Font Families
@Composable
fun legendDisplayFontFamily() = FontFamily(
    Font(Res.font.cinzel_decorative_bold, weight = FontWeight.Bold)
)

@Composable
fun rusticTitleFontFamily() = FontFamily(
    Font(Res.font.im_fell_english_regular, weight = FontWeight.Normal)
)

@Composable
fun tomeBodyFontFamily() = FontFamily(
    Font(Res.font.alegreya_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(Res.font.alegreya_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.alegreya_bold, weight = FontWeight.Bold, style = FontStyle.Normal)
)

// 2. Map fonts to Material 3 Typography
@Composable
fun legendInTheMistTypography(): Typography {
    val displayFont = legendDisplayFontFamily()
    val titleFont = rusticTitleFontFamily()
    val bodyFont = tomeBodyFontFamily()

    return Typography(
        // Great for big screen headers or Character Names
        displayLarge = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Bold,
            fontSize = 48.sp,
            lineHeight = 56.sp,
            letterSpacing = 1.sp
        ),
        displayMedium = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.5.sp
        ),
        displaySmall = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            lineHeight = 36.sp
        ),

        // Great for Theme Books (Logos/Mist), Quest Titles, and Location Names
        headlineLarge = TextStyle(
            fontFamily = titleFont,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = titleFont,
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            lineHeight = 34.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = titleFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),

        // Great for Card Headers, Move Names, and NPC Names
        titleLarge = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            lineHeight = 28.sp
        ),
        titleMedium = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic, // Italicized sub-headers look very "storybook"
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),

        // Main rules text, character descriptions, and lore
        bodyLarge = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp, // Slightly larger than standard M3 because serif fonts run small
            lineHeight = 26.sp,
            letterSpacing = 0.25.sp
        ),
        bodyMedium = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic, // Good for flavor text / quotes
            fontSize = 14.sp,
            lineHeight = 18.sp,
            letterSpacing = 0.4.sp
        ),

        // Perfect for TTRPG "Power Tags", "Weakness Tags", and Statuses
        labelLarge = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.5.sp
        ),
        labelMedium = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}