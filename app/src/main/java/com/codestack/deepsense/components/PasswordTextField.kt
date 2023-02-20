package com.codestack.deepsense.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.codestack.deepsense.R

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    isEmpty: Boolean = false,
    isSigningUp: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = { newPassword -> onPasswordChange(newPassword) },
        leadingIcon = { Icon(imageVector = Icons.Outlined.Lock, contentDescription = null) },
        label = { Text(text = "Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = MaterialTheme.shapes.medium,
        trailingIcon = {
            val painter = if (passwordVisible) painterResource(id = R.drawable.ic_visibility)
            else painterResource(id = R.drawable.ic_visibility_off)
            IconButton(
                onClick = { passwordVisible = !passwordVisible }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painter,
                    contentDescription = "visibility"
                )
            }
        },
        isError = isEmpty,
        enabled = !isSigningUp
    )
}
