package com.codestack.deepsense.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp, 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .padding(6.dp)
            .weight(1f)
        ) {
            Text(
                text = "Hello ",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
            Text(
                text = user,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "!",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )
        }
        IconButton(
            onClick = { navController.navigate(Screens.Settings.route) },
        ) {
            Icon(
                Icons.Outlined.AccountCircle,
                contentDescription = "Profile Button",
                modifier = Modifier.size(32.dp)
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