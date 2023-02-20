package com.codestack.deepsense.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    text: String = "Sign Up with Google",
    textClicked: String = "Signing Up with Google",
    icon: Int = R.drawable.ic_google_logo,
    isSigningUp: Boolean = false,
    onClick: () -> Unit,
) {
    var clicked by remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            onClick()
            clicked = !clicked
        },
        enabled = !isSigningUp,
        modifier = modifier
            .fillMaxWidth()
            .padding(40.dp, 10.dp)
    ) {
        Icon(
            modifier = modifier.size(24.dp),
            painter = painterResource(id = icon),
            contentDescription = "platform icon",
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = if (clicked) textClicked else text,
            modifier = Modifier.padding(0.dp, 5.dp),
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}