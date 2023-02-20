package com.codestack.deepsense.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.ui.theme.DeepSenseTheme

@Composable
fun MainTopAppBar(
    navController: NavHostController
) {
    val user = "Chamath"
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(24.dp, 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Text(text = "Hello ", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
            Text(
                text = user,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = "!", fontSize = MaterialTheme.typography.headlineSmall.fontSize)
        }
        IconButton(
            onClick = { navController.navigate(Screens.Settings.route) }
        ) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile Button",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainTopAppBarPreview() {
    DeepSenseTheme {
        MainTopAppBar(navController = rememberNavController())
    }
}