package com.vixiloc.vixitask.presentations.screens

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.setValue
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
fun AddScreen(navHostController: NavHostController) {
    val title = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val context = LocalContext.current

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth - ${mMonth + 1} - $mYear"
        }, mYear, mMonth, mDay
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
                title = stringResource(R.string.create_task),
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
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
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
                            text = stringResource(id = R.string.create),
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
fun AddScreenPreview() {
    VixitaskTheme {
        AddScreen(navHostController = rememberNavController())
    }
}