package com.vixiloc.vixitask.presentations.screens

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.data.model.Tasks
import com.vixiloc.vixitask.domain.navigations.MainDestination
import com.vixiloc.vixitask.presentations.components.NothingToShow
import com.vixiloc.vixitask.presentations.components.TaskActiveCard
import com.vixiloc.vixitask.presentations.components.TaskInactiveCard
import com.vixiloc.vixitask.presentations.components.TextFieldSearch
import com.vixiloc.vixitask.presentations.components.TopBarTransparent
import com.vixiloc.vixitask.presentations.viewmodels.HomeScreenVm
import com.vixiloc.vixitask.ui.theme.VixitaskTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeScreenVm = getViewModel()) {
    val scope = rememberCoroutineScope()
    val visible = remember { mutableStateOf(false) }
    val list by viewModel.tasks.collectAsState(initial = emptyList())
    val context = LocalContext.current

    Scaffold(topBar = {
        Column(modifier = Modifier.fillMaxWidth()) {
            TopBarTransparent(
                icon = {
                    IconButton(onClick = { navHostController.navigate(MainDestination.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { visible.value = !visible.value }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search",
                            modifier = Modifier.size(30.dp)
                        )
                    }
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
            val searchValue = viewModel.searchValue.collectAsState().value
            AnimatedVisibility(visible = visible.value) {
                TextFieldSearch(
                    modifier = Modifier.padding(horizontal = 30.dp),
                    value = searchValue,
                    onChange = {
                        viewModel.search(it)
                    },
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

            val activeTasks = list.filter { !it.isDone }
            val inActiveTasks = list.filter { it.isDone }

            if (activeTasks.isEmpty()) {
                item {
                    NothingToShow()
                }
            } else {
                items(activeTasks) { task ->
                    TaskActiveCard(
                        title = task.title,
                        dateTime = task.date,
                        onClick = {
                            scope.launch {
                                navHostController.navigate(MainDestination.Detail.createRoute(task.id!!))
                            }
                        },
                        modifier = Modifier.animateItemPlacement(),
                        onDone = {
                            scope.launch {
                                viewModel.done(task.id!!)
                            }
                        },
                        onDelete = {
                            scope.launch {
                                viewModel.delete(task)
                            }
                        },
                        onShare = {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Hello, this is my task:\n" +
                                            "Title : ${task.title}\n" +
                                            "At : ${task.date}"
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }
                    )
                }
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

            if (inActiveTasks.isEmpty()) {
                item {
                    NothingToShow()
                }
            } else {
                items(inActiveTasks) { task ->
                    TaskInactiveCard(
                        title = task.title,
                        dateTime = task.date,
                        onClick = {
                            scope.launch {
                                navHostController.navigate(MainDestination.Detail.createRoute(task.id!!))
                            }
                        },
                        modifier = Modifier.animateItemPlacement(),
                        onDelete = {
                            scope.launch {
                                viewModel.delete(task)
                            }
                        },
                        onShare = {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Task : ${task.title}\n" +
                                            "At : ${task.date}"
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }
                    )
                }
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