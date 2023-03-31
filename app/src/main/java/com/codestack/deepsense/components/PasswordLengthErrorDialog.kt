package com.codestack.deepsense.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun PasswordLengthErrorDialog(
    onDismissReq: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissReq,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.sentiment_content),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        },
        title = {
            Text(text = "OOOOPS!")
        },
        text = {
            Text(text = "Password must be at least 8 characters")
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismissReq
            ) {
                Text("OK")
            }
        }
    )
}

@Preview
@Composable
fun PasswordLengthErrorDialogPreview() {
    PasswordLengthErrorDialog({})
}