package com.chillieman.chilliekeeper.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun ChillieKeeperTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = KeeperYellow,
            onPrimary = Color.Black,
            secondary = Color.White,
            onSecondary = Color.White,
            tertiary = Color.Black,
            onTertiary = Color.White,
            background = Color.Black
        ),
        typography = Typography,
        content = content
    )
}