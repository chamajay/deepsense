package com.codestack.deepsense.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.codestack.deepsense.navigation.Screens

@Composable
fun AuthTopNavButtons(navController: NavHostController) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        // Back button
        OutlinedButton(onClick = { navController.popBackStack() }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Skip button
        OutlinedButton(
            onClick = {
                navController.navigate(Screens.Main.route) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        ) {
            Text(text = "Skip")
        }
    }
}