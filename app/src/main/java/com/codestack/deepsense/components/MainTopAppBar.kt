package com.codestack.deepsense.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.ui.theme.DeepSenseTheme

@Composable
fun MainTopAppBar(
    navController: NavHostController
) {
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append("Hello ")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        append("Chamath")
                    }
                    append("!")
                }, fontSize = 20.sp
            )
        },
        actions = {
            IconButton(onClick = { navController.navigate(Screens.Settings.route) }) {
                Icon(
                    Icons.Outlined.AccountCircle,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colorScheme.background
    )

}

@Preview(showBackground = true)
@Composable
fun MainTopAppBarPreview() {
    DeepSenseTheme {
        MainTopAppBar(navController = rememberNavController())
    }
}