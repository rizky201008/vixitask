package com.vixiloc.vixitask.presentations.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.components.TextFieldDefault
import com.vixiloc.vixitask.presentations.components.TopBarBack
import com.vixiloc.vixitask.ui.theme.VixitaskTheme
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(navHostController: NavHostController) {
    val title = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("01 - 10 - 2030") }
    val year: Int
    val month: Int
    val day: Int
    val context = LocalContext.current

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth - ${month + 1} - $year"
        }, year, month, day
    )

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
                title = stringResource(R.string.update_task),
            )
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
                    TextFieldDefault(
                        modifier = Modifier.padding(horizontal = 30.dp),
                        label = stringResource(R.string.task_title),
                        value = title.value,
                        onChange = { title.value = it },
                        leadingIcon = {
                            Icon(imageVector = Icons.Outlined.Notes, contentDescription = null)
                        }
                    )
                }

                item {
                    TextFieldDefault(
                        modifier = Modifier
                            .padding(horizontal = 30.dp),
                        label = stringResource(R.string.date),
                        value = date.value,
                        onChange = { date.value = it },
                        leadingIcon = {
                            IconButton(onClick = {
                                datePickerDialog.show()
                            }) {
                                Icon(
                                    imageVector = Icons.Outlined.CalendarMonth,
                                    contentDescription = null
                                )
                            }
                        },
                        readOnly = true
                    )
                }

                item {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 20.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.update),
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 28.sp
                        )
                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun UpdateScreenPreview() {
    VixitaskTheme {
        UpdateScreen(navHostController = rememberNavController())
    }
}