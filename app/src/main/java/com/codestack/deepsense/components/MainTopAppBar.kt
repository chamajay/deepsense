package com.codestack.deepsense.components

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codestack.deepsense.core.Constants.TAG
import com.codestack.deepsense.navigation.Screens
import com.codestack.deepsense.presentation.shared.ProfileViewModel
import com.codestack.deepsense.ui.theme.DeepSenseTheme

@Composable
fun MainTopAppBar(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    TopAppBar(
        title = {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle()) {
                        append("Hello ")
                    }
                    withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                        // get the first part of the name
                        append(viewModel.displayName.split(" ")[0])
                    }
                    append("!")
                }, fontSize = 20.sp
            )
        },
        actions = {
            IconButton(
                onClick = { navController.navigate(Screens.Settings.route) }
            ) {
                if (viewModel.photoUrl != "null") {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(viewModel.photoUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .width(24.dp)
                            .height(24.dp)
                    )
                } else {
                    Icon(
                        Icons.Outlined.AccountCircle,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(30.dp)
                    )
                }
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

