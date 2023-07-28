package com.ibragimova.coreuicompose.uikit.listtile

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibragimova.corecommon.container.ImageValue
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.corecommon.container.textValue
import com.ibragimova.corecommon.strings.StringKey
import com.ibragimova.coreuicompose.button.SimpleButtonContent
import com.ibragimova.coreuicompose.tools.TileState
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuitheme.compose.AppTheme
import com.ibragimova.coreuitheme.compose.PreviewAppTheme

data class MessageBannerState(
    val image: ImageValue? = null,
    val title: TextValue? = null,
    val description: TextValue? = null,
    val button: TextValue? = null,
    val modifier: Modifier = Modifier,
    val onClick: (() -> Unit)? = null,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        MessageBannerTile(modifier, this)
    }

    companion object {

        fun defaultState(onClick: () -> Unit) = MessageBannerState(
            description = StringKey.ErrorLoadFail.textValue(),
            button = StringKey.ActionReload.textValue(),
            onClick = onClick,
        )
    }
}

object MessageBannerConfig {

    @Composable
    fun colors() = CardDefaults.cardColors(
        containerColor = AppTheme.specificColorScheme.uiContentBg,
        contentColor = AppTheme.specificColorScheme.uiContentBg,
    )

    @Composable
    fun elevation() = CardDefaults.cardElevation()
}

@Composable
fun MessageBannerTile(
    modifier: Modifier = Modifier,
    data: MessageBannerState,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = MessageBannerConfig.colors(),
        elevation = MessageBannerConfig.elevation(),
        shape = AppTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.padding(top = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val image = data.image

            image?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = it.get(),
                    contentDescription = "Message banner image",
                    modifier = Modifier.size(124.dp),
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if (data.title != null) {
                if (image != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Title(data.title)
                if (image != null && data.description == null && data.button == null) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (data.description != null) {
                when {
                    data.title != null -> Spacer(modifier = Modifier.height(4.dp))
                    image != null -> Spacer(modifier = Modifier.height(8.dp))
                }
                Description(data.description)
                when {
                    image != null && data.button == null -> Spacer(modifier = Modifier.height(8.dp))
                }
            }
            if (data.button != null) {
                when {
                    data.title != null || data.description != null -> Spacer(modifier = Modifier.height(16.dp))
                    image != null -> Spacer(modifier = Modifier.height(8.dp))
                }
                Button(data.button, data.onClick ?: {})
            }
        }
    }
}

@Composable
private fun Title(title: TextValue) {
    Text(
        text = title.get(),
        color = AppTheme.specificColorScheme.textPrimary,
        style = AppTheme.specificTypography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Description(description: TextValue) {
    Text(
        text = description.get(),
        color = AppTheme.specificColorScheme.textSecondary,
        style = AppTheme.specificTypography.bodyMedium,
        textAlign = TextAlign.Center,
        maxLines = 3,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun Button(button: TextValue, onClick: () -> Unit) {
    OutlinedButton(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        onClick = onClick,
    ) { SimpleButtonContent(button) }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        MessageBannerState(
            image = AppTheme.specificIcons.error.toImageValue(),
            title = "Message title text".textValue(),
            description = "Message description text".textValue(),
            button = "Button".textValue(),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            onClick = {},
        ).Content(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
    }
}