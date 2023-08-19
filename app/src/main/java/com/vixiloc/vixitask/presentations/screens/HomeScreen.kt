package com.vixiloc.vixitask.presentations.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.domain.navigations.MainDestination
import com.vixiloc.vixitask.presentations.components.TaskActiveCard
import com.vixiloc.vixitask.presentations.components.TaskInactiveCard
import com.vixiloc.vixitask.presentations.components.TextFieldDefault
import com.vixiloc.vixitask.presentations.components.TopBarTransparent
import com.vixiloc.vixitask.ui.theme.VixitaskTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController) {
    val visible = remember { mutableStateOf(false) }
    Scaffold(topBar = {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopBarTransparent(
                icon = {
                    IconButton(onClick = { visible.value = !visible.value }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    Row(
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.onBackground,
                                shape = MaterialTheme.shapes.extraLarge
                            )
                            .height(50.dp)
                            .clickable {
                                navHostController.navigate(MainDestination.Add.route)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.AddCircle,
                            contentDescription = "Create",
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .size(35.dp)
                        )
                        Text(
                            text = stringResource(R.string.create),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    }
                }
            )
            val searchValue = remember { mutableStateOf("") }
            AnimatedVisibility(visible = visible.value) {
                TextFieldDefault(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    label = stringResource(R.string.search_tasks),
                    value = searchValue.value,
                    onChange = { searchValue.value = it },
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Search, contentDescription = null)
                    }
                )
            }
        }
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.active_tasks),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            items(15) {
                TaskActiveCard(
                    title = "Ini adalah tugas yang ke $it kali nya ya jadi selamat datang ya",
                    dateTime = "08 - 10 - 2002",
                    onClick = {
                        navHostController.navigate(MainDestination.Detail.route)
                    },
                    modifier = Modifier.animateItemPlacement()
                )
            }

            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.inactive_tasks),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            items(15) {
                TaskInactiveCard(
                    title = "Ini adalah tugas yang ke $it kali nya ya jadi selamat datang ya",
                    dateTime = "08 - 10 - 2002",
                    onClick = {
                        navHostController.navigate(MainDestination.Detail.route)
                    },
                    modifier = Modifier.animateItemPlacement()
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomeScreenPreview() {
    VixitaskTheme {
        HomeScreen(navHostController = rememberNavController())
    }
}