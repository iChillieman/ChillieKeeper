package com.chillieman.chilliekeeper.ui.signin

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties
import com.chillieman.chilliekeeper.R

@Composable
fun ErrorDialog(
    onConfirmation: () -> Unit,
    dialogTitleId: Int,
    dialogErrors: List<Int>,
) {
    val title = stringResource(id = dialogTitleId)
    val errorText = StringBuilder()
    dialogErrors.forEachIndexed { index, resId ->
        // Add line break if there are multiple errors to display.
        if(index > 0) {
            errorText.append("\n")
        }
        errorText.append(stringResource(id = resId))
    }
    AlertDialog(
        title = {
            Text(text = title)
        },
        text = {
            Text(text = errorText.toString())
        },
        onDismissRequest = {}, // Only Dismiss on confirmation of error
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("OK")
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    )
}