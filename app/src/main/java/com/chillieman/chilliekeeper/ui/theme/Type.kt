package com.chillieman.chilliekeeper.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.chillieman.chilliekeeper.R

val OverpassFont = FontFamily(
    Font(R.font.overpass_regular),
    Font(R.font.overpass_italic, style = FontStyle.Italic),
    Font(R.font.overpass_medium, FontWeight.Medium),
    Font(R.font.overpass_bold, FontWeight.Bold)
)

private val defaultTypography = Typography()
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = OverpassFont),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = OverpassFont),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = OverpassFont),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = OverpassFont),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = OverpassFont),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = OverpassFont),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = OverpassFont),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = OverpassFont),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = OverpassFont),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = OverpassFont),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = OverpassFont),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = OverpassFont),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = OverpassFont),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = OverpassFont),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = OverpassFont)
)