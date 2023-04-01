package com.codestack.deepsense.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
fun NotEnoughDataSm() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.Info,
            tint = Color(0xFFFF8400),
            contentDescription = "Info",
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "I need more data to make predictions",
            fontSize = MaterialTheme.typography.titleMedium.fontSize
        )
    }
}


@Preview
@Composable
fun NotEnoughDataSmPreview() {
    NotEnoughDataSm()
}