package com.ibragimova.coreuicompose.uikit.duplicate

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.ibragimova.corecommon.container.IconValue
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.tools.insetAllExcludeBottom
import com.ibragimova.coreuitheme.compose.AppTheme

object SimpleTopAppBarConfig {

    val ContainerColorAlpha = .9f

    @Composable
    fun surfaceColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.uiContentBg,
        scrolledContainerColor = AppTheme.specificColorScheme.uiContentBg,
        navigationIconContentColor = AppTheme.specificColorScheme.textPrimary,
        titleContentColor = AppTheme.specificColorScheme.textPrimary,
        actionIconContentColor = AppTheme.specificColorScheme.textPrimary,
    )

    @Composable
    fun transparentSurfaceColors() = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.uiContentBg.copy(alpha = ContainerColorAlpha),
        scrolledContainerColor = AppTheme.specificColorScheme.uiContentBg,
        navigationIconContentColor = AppTheme.specificColorScheme.textPrimary,
        titleContentColor = AppTheme.specificColorScheme.textPrimary,
        actionIconContentColor = AppTheme.specificColorScheme.textPrimary,
    )

    @Composable
    fun whiteColors(
        navigationIconContentColor: Color = AppTheme.specificColorScheme.textPrimary,
    ) = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.white,
        scrolledContainerColor = AppTheme.specificColorScheme.white,
        titleContentColor = AppTheme.specificColorScheme.textPrimary,
        navigationIconContentColor = navigationIconContentColor,
        actionIconContentColor = AppTheme.specificColorScheme.textPrimary,
    )

    @Composable
    fun transparentColors(
        navigationIconContentColor: Color = AppTheme.specificColorScheme.textPrimary,
    ) = SimpleTopAppBarColors(
        containerColor = AppTheme.specificColorScheme.transparent,
        scrolledContainerColor = AppTheme.specificColorScheme.transparent,
        navigationIconContentColor = navigationIconContentColor,
        titleContentColor = AppTheme.specificColorScheme.textPrimary,
        actionIconContentColor = AppTheme.specificColorScheme.textPrimary,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: TextValue?,
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    windowInsets: WindowInsets = insetAllExcludeBottom(),
    colors: SimpleTopAppBarColors = SimpleTopAppBarConfig.transparentSurfaceColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    additional: @Composable () -> Unit = {},
) {
    SimpleTopAppBar(
        title = { title?.get()?.let { Text(it) } },
        titleTextStyle = titleTextStyle,
        navigationIcon = navigationIcon,
        actions = actions,
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
        additional = additional,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(
    title: @Composable () -> Unit,
    titleTextStyle: TextStyle = AppTheme.specificTypography.titleMedium,
    navigationIcon: Pair<IconValue, OnBackIconClick>? = null,
    actions: List<ActionIconData> = emptyList(),
    windowInsets: WindowInsets = insetAllExcludeBottom(),
    colors: SimpleTopAppBarColors = SimpleTopAppBarConfig.transparentSurfaceColors(),
    scrollBehavior: TopAppBarScrollBehavior,
    additional: @Composable () -> Unit = {},
) {
    SingleRowTopAppBar(
        title = title,
        titleTextStyle = titleTextStyle,
        navigationIcon = {
            if (navigationIcon != null) {
                TopAppBarNavIcon(
                    iconValue = navigationIcon.first,
                    onClick = { navigationIcon.second() },
                )
            }
        },
        actions = {
            if (actions.toList().isNotEmpty()) Row {
                actions.toList().forEach {
                    TopAppBarActionIcon(data = it)
                }
            }
        },
        windowInsets = windowInsets,
        colors = colors,
        scrollBehavior = scrollBehavior,
        additional = additional,
    )
}