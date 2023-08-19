package com.vixiloc.vixitask.presentations.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.domain.navigations.MainDestination
import com.vixiloc.vixitask.presentations.components.TopBarBack
import com.vixiloc.vixitask.ui.theme.VixitaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarBack(
                icon = {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                title = stringResource(R.string.detail_task),
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navHostController.navigate(MainDestination.Update.route) }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = "Update task")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
            ) {
                item {
                    Text(
                        text = "This is the example of tasks and than this is the detail yah",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 30.dp),
                        color = Color.Black,
                        fontSize = 28.sp
                    )
                }
                item {
                    Text(
                        text = "08 - 12 - 2023",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                            .padding(top = 30.dp),
                        color = Color.Black,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview() {
    VixitaskTheme {
        DetailScreen(navHostController = rememberNavController())
    }
}