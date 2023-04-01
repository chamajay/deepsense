package com.codestack.deepsense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
            contentDescription = "Error"
        )
        Text(
            text = "Can't connect to the server",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}

@Preview
@Composable
fun ServerErrorLgPreview() {
    ServerErrorLg()
}