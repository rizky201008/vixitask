package com.vixiloc.vixitask.presentations.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.components.TopBarBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OsLicense(navHostController: NavHostController) {
    Scaffold(
        topBar = {
            TopBarBack(icon = {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = null)
                }
            }, stringResource(id = R.string.os_license))
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = stringResource(R.string.license),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        }
    }
}