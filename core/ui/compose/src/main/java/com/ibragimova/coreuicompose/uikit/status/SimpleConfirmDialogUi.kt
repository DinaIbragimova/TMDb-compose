package com.ibragimova.coreuicompose.uikit.status

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ibragimova.corecommon.container.TextValue
import com.ibragimova.coreuicompose.button.SimpleButtonContent
import com.ibragimova.coreuicompose.tools.get
import com.ibragimova.coreuicompose.uikit.button.SimpleButtonInlineM
import com.ibragimova.coreuitheme.compose.AppTheme

@Composable
fun SimpleConfirmDialogUi(
    title: TextValue,
    text: TextValue,
    confirmButtonText: TextValue,
    dismissButtonText: TextValue,
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Text(
                text = title.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.titleLarge,
                color = AppTheme.specificColorScheme.textPrimary,
            )
        },
        text = {
            Text(
                text = text.get(),
                modifier = Modifier.fillMaxWidth(),
                style = AppTheme.specificTypography.bodyMedium,
                color = AppTheme.specificColorScheme.textPrimary,
            )
        },
        confirmButton = {
            SimpleButtonInlineM(onClick = onConfirmRequest) {
                SimpleButtonContent(confirmButtonText)
            }
        },
        dismissButton = {
            SimpleButtonInlineM(onClick = onDismissRequest) {
                SimpleButtonContent(dismissButtonText)
            }
        },
    )
}