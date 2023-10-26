package com.vixiloc.vixitask.presentations.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.MainDestination
import com.vixiloc.vixitask.presentations.components.TopBarBack
import com.vixiloc.vixitask.presentations.ui.theme.VixitaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navHostController: NavHostController) {
    val uriHandler = LocalUriHandler.current
    Scaffold(
        topBar = {
            TopBarBack(title = stringResource(R.string.information), icon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
                }
            })
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(color = Color.White, shape = MaterialTheme.shapes.extraLarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .padding(all = 10.dp)
                            .clip(CircleShape)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_launcher),
                            contentDescription = null,
                            Modifier
                                .size(100.dp)
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.app_version),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Black,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                }
            }
            item {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(shape = MaterialTheme.shapes.extraLarge)
                        .clickable { uriHandler.openUri("https://vixiloc.co.id/2023/08/28/vixitask-privacy-policy/") },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        supportingColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    headlineText = {
                        Text(
                            text = stringResource(R.string.privacy_policy),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    supportingText = {
                        Text(
                            text = stringResource(R.string.privacy_policy_support),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
            }
            item {
                ListItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(shape = MaterialTheme.shapes.extraLarge)
                        .clickable { navHostController.navigate(MainDestination.OsLicense.route) },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        headlineColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        supportingColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    headlineText = {
                        Text(
                            text = stringResource(R.string.os_license),
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    supportingText = {
                        Text(
                            text = stringResource(R.string.os_license_support),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun AboutScreenPreview() {
    VixitaskTheme {
        AboutScreen(navHostController = rememberNavController())
    }
}