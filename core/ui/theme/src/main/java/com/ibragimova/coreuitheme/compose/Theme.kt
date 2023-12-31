package com.ibragimova.coreuitheme.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import com.ibragimova.corecommon.container.IconValue
import com.ibragimova.coreuitheme.compose.ext.toColorScheme
import com.ibragimova.coreuitheme.compose.ext.toSpecificColorScheme

val LocalWindowSizeClass = staticCompositionLocalOf<WindowSizeClass> {
    error("CompositionLocal LocalWindowSizeClass not present")
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PreviewAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    BoxWithConstraints {
        AppTheme(
            windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight)),
            isDarkTheme = darkTheme,
        ) {
            Surface(
                color = AppTheme.specificColorScheme.uiContentBg,
                contentColor = AppTheme.specificColorScheme.textPrimary,
                content = content,
            )
        }
    }
}

@Composable
fun AppTheme(
    windowSizeClass: WindowSizeClass,
    isDynamicTheme: Boolean = false,
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val (_, specificColorScheme) = if (isDynamicTheme) {
        val colorScheme = when {
            isDarkTheme -> dynamicDarkColorScheme(LocalContext.current).copy(
                outline = DarkSpecificColorScheme.outlineBorderBase,
                outlineVariant = DarkSpecificColorScheme.outlineBorderBase,
            )

            else -> dynamicLightColorScheme(LocalContext.current).copy(
                outline = LightSpecificColorScheme.outlineBorderBase,
                outlineVariant = LightSpecificColorScheme.outlineBorderBase,
            )
        }
        val specificColorScheme = colorScheme.toSpecificColorScheme(!isDarkTheme)
        colorScheme to specificColorScheme
    } else {
        when {
            isDarkTheme -> DarkColorScheme to DarkSpecificColorScheme
            else -> LightColorScheme to LightSpecificColorScheme
        }
    }

    CompositionLocalProvider(
        LocalSpecificColorScheme provides specificColorScheme,
        LocalSpecificTypography provides DefaultSpecificTypography,
        LocalWindowSizeClass provides windowSizeClass,
    ) {
        MaterialTheme(
            colorScheme = specificColorScheme.toColorScheme(),
            shapes = AppShapes,
            typography = DefaultTypography,
            content = content,
        )
    }
}

object AppTheme {

    val specificColorScheme: SpecificColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalSpecificColorScheme.current

    val specificTypography: SpecificTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalSpecificTypography.current

    val specificIcons = SpecificIcons

    val specificValues = SpecificValues

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.shapes

    internal val colorScheme: ColorScheme
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.colorScheme

    internal val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = MaterialTheme.typography
}

// Handy functions when there is some logic in resource selection:
// colors { if (condition1) white else black }
inline fun icons(block: SpecificIcons.() -> IconValue): IconValue =
    AppTheme.specificIcons.block()

@Composable
inline fun colors(block: SpecificColorScheme.() -> Color): Color =
    AppTheme.specificColorScheme.block()

@Composable
inline fun typography(block: SpecificTypography.() -> TextStyle): TextStyle =
    AppTheme.specificTypography.block()