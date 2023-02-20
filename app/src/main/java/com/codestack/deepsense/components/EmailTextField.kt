package com.codestack.deepsense.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun EmailTextField(
    email: String,
    onEmailChanged: (String) -> Unit,
    isInvalid: Boolean = false,
    isSigningUp: Boolean = false
) {
    OutlinedTextField(
        value = email,
        onValueChange = { newEmail -> onEmailChanged(newEmail) },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { Icon(imageVector = Icons.Outlined.Email, contentDescription = null) },
        label = { Text(text = "Email") },
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        isError = isInvalid,
        enabled = !isSigningUp
    )
}