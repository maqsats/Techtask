package com.intechbel.techtask.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object AppTheme {
    val palette
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    val typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography

    val dimens: Dimens
        @Composable
        @ReadOnlyComposable
        get() = LocalDimens.current
}

private val LocalDimens = staticCompositionLocalOf { Dimens() }

@Composable
fun AppTheme(
    isNightModeEnabled: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isNightModeEnabled) darkColorScheme() else lightColorScheme()
    val typography = MaterialTheme.typography

    CompositionLocalProvider(
        LocalDimens provides Dimens(),
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = typography,
            content = content
        )
    }
}

