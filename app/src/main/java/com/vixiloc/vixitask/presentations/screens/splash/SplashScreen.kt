package com.vixiloc.vixitask.presentations.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.MainDestination
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navHostController: NavHostController) {
    var isPlaying by remember { mutableStateOf(true) }
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.vixitask_animation
        )
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )

    LaunchedEffect(key1 = progress) {
        if (progress == 0f) {
            isPlaying = true
        }
        if (progress == 1f) {
            isPlaying = false
        }
    }

    LaunchedEffect(key1 = true) {
        delay(4000)
        navHostController.popBackStack()
        navHostController.navigate(MainDestination.Home.route)
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            modifier = Modifier
                .size(280.dp),
            composition = composition,
            progress = { progress }
        )
    }
}