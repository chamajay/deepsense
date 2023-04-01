package com.codestack.deepsense.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ServerErrorLg() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.Warning,
            tint = Color(0xFFE21818),
            contentDescription = "Error",
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "Can't connect to the server",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        )
    }
}

@Preview
@Composable
fun ServerErrorLgPreview() {
    ServerErrorLg()
}