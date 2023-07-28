package com.ibragimova.coreuicompose.button

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ibragimova.corecommon.container.IconValue
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.corecommon.container.textValue
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.uikit.button.SimpleButtonActionL
import com.ibragimova.coreuicompose.uikit.button.SimpleButtonInlineL
import com.ibragimova.coreuitheme.compose.AppTheme
import com.ibragimova.coreuitheme.compose.PreviewAppTheme

@Composable
fun SimpleButtonContent(
    text: TextValue?,
    textColor: Color = LocalContentColor.current,
    iconLeft: IconValue? = null,
    iconRight: IconValue? = null,
    iconTint: Color = Color.Unspecified,
) {
    SimpleButtonContent(
        text = text,
        textColor = textColor,
        contentLeft = iconLeft?.get()?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = "Button left icon",
                    tint = iconTint,
                    modifier = Modifier.size(getContentHeight()),
                )
            }
        },
        contentRight = iconRight?.get()?.let {
            {
                Icon(
                    painter = it,
                    contentDescription = "Button right icon",
                    tint = iconTint,
                    modifier = Modifier.size(getContentHeight()),
                )
            }
        },
    )
}

@Composable
fun SimpleButtonContent(
    text: TextValue?,
    textColor: Color = LocalContentColor.current,
    contentLeft: @Composable (() -> Unit)? = null,
    contentRight: @Composable (() -> Unit)? = null,
) {
    val textPaddingStart = when (contentLeft) {
        null -> 4.dp
        else -> 8.dp
    }
    val textPaddingEnd = when (contentRight) {
        null -> 4.dp
        else -> 8.dp
    }

    contentLeft?.invoke()
    text?.get()?.let {
        Text(
            text = it,
            color = textColor,
            style = LocalTextStyle.current,
            modifier = Modifier
                .padding(start = textPaddingStart, end = textPaddingEnd)
                .height(getContentHeight()),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
    contentRight?.invoke()
}

@Composable
fun SimpleButtonContentLoader() {
    CircularProgressIndicator(
        color = LocalContentColor.current,
        modifier = Modifier.size(getContentHeight()),
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SimpleButtonContentWithLoader(
    isLoading: Boolean,
    text: TextValue?,
    textColor: Color = LocalContentColor.current,
    iconLeft: IconValue? = null,
    iconRight: IconValue? = null,
    iconTint: Color = Color.Unspecified,
) {
    AnimatedContent(targetState = isLoading) {
        if (it) SimpleButtonContentLoader()
        else SimpleButtonContent(
            text = text,
            textColor = textColor,
            iconLeft = iconLeft,
            iconRight = iconRight,
            iconTint = iconTint,
        )
    }
}

@Composable
private fun getContentHeight() = LocalTextStyle.current.lineHeight.value.dp + 3.5.dp

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        Row {
            SimpleButtonActionL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContent("Simple button".textValue()) }

            SimpleButtonActionL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContent(null, iconLeft = AppTheme.specificIcons.add) }

            SimpleButtonActionL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContentLoader() }
            SimpleButtonInlineL(
                modifier = Modifier.padding(horizontal = 4.dp),
                onClick = {},
            ) { SimpleButtonContentLoader() }
        }
    }
}