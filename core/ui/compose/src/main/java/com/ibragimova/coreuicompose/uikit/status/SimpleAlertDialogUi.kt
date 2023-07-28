package com.ibragimova.coreuicompose.uikit.status

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.coreuicompose.button.SimpleButtonContent
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.uikit.button.SimpleButtonInlineS
import com.ibragimova.coreuitheme.compose.AppTheme

@Composable
fun SimpleAlertDialogUi(
    text: TextValue,
    buttonText: TextValue,
    onClickRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onClickRequest,
        text = {
            Text(
                text = text.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.bodyLarge,
                color = AppTheme.specificColorScheme.textPrimary
            )
        },
        dismissButton = {
            SimpleButtonInlineS(onClick = onClickRequest) {
                SimpleButtonContent(buttonText)
            }
        },
        confirmButton = {},
    )
}