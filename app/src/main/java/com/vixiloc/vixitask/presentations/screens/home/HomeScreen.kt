package com.vixiloc.vixitask.presentations.screens.home

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.MainDestination
import com.vixiloc.vixitask.presentations.components.NothingToShow
import com.vixiloc.vixitask.presentations.components.TaskActiveCard
import com.vixiloc.vixitask.presentations.components.TaskInactiveCard
import com.vixiloc.vixitask.presentations.ui.theme.VixitaskTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: HomeScreenVm = getViewModel()) {
    val scope = rememberCoroutineScope()
    val visible = remember { mutableStateOf(false) }
    val list by viewModel.tasks.collectAsState(initial = emptyList())
    val context = LocalContext.current

    Box(
        modifier = Modifier
    ) {

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    VixitaskTheme {
        HomeScreen(navHostController = rememberNavController())
    }
}