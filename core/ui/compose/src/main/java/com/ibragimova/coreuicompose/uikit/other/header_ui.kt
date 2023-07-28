package com.ibragimova.coreuicompose.uikit.other

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuitheme.compose.AppTheme

@Composable
fun Header(
    value: TextValue,
    modifier: Modifier,
) {
    Text(
        text = value.get(),
        style = AppTheme.specificTypography.headlineMedium,
        color = AppTheme.specificColorScheme.textPrimary,
        modifier = modifier,
    )
}

@Composable
fun Header2(
    value: TextValue,
    modifier: Modifier,
) {
    Text(
        text = value.get(),
        style = AppTheme.specificTypography.headlineSmall,
        color = AppTheme.specificColorScheme.textPrimary,
        modifier = modifier,
    )
}

@Composable
fun Header3(
    value: TextValue,
    modifier: Modifier,
) {
    Text(
        text = value.get(),
        style = AppTheme.specificTypography.titleLarge,
        color = AppTheme.specificColorScheme.textPrimary,
        modifier = modifier,
    )
}

@Composable
fun Header4(
    value: TextValue,
    modifier: Modifier,
) {
    Text(
        text = value.get(),
        style = AppTheme.specificTypography.titleMedium,
        color = AppTheme.specificColorScheme.textPrimary,
        modifier = modifier,
    )
}

@Composable
fun Header5(
    value: TextValue,
    modifier: Modifier,
) {
    Text(
        text = value.get(),
        style = AppTheme.specificTypography.titleSmall,
        color = AppTheme.specificColorScheme.textPrimary,
        modifier = modifier,
    )
}