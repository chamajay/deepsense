package com.codestack.deepsense.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
fun FeatureIncomingDialog(
    onDismissReq: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissReq,
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_rocket_launch_outlined),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            ) },
        title = {
            Text(text = "Coming Soon!")
        },
        text = {
            Text(text = "Our team is on it, feature incoming..")
        },
        confirmButton = {},
        dismissButton = {
            TextButton(
                onClick = onDismissReq
            ) {
                Text("Dismiss")
            }
        }
    )

}

@Preview
@Composable
fun FeatureIncomingDialogPreview() {
    FeatureIncomingDialog({})
}