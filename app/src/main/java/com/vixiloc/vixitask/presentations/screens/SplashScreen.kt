package com.vixiloc.vixitask.presentations.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.domain.navigations.MainDestination
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navHostController.popBackStack()
        navHostController.navigate(MainDestination.Home.route)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontWeight = FontWeight.Bold,
            fontSize = 54.sp
        )
    }
}