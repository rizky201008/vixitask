package com.vixiloc.vixitask.presentations.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vixiloc.vixitask.R
import com.vixiloc.vixitask.presentations.ui.theme.VixitaskTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldForm(
    readOnly: Boolean = false,
    modifier: Modifier,
    label: String = "",
    onChange: (String) -> Unit = {},
    value: String = "",
    leadingIcon: @Composable () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 5.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = { onChange(it) },
            leadingIcon = leadingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            maxLines = 5,
            readOnly = readOnly
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldSearch(
    modifier: Modifier,
    onChange: (String) -> Unit = {},
    value: String = "",
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = { onChange(it) },
            leadingIcon = { Icon(imageVector = Icons.Outlined.Search, contentDescription = "Search Icon") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_tasks),
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                cursorColor = MaterialTheme.colorScheme.primary,
            ),
            shape = MaterialTheme.shapes.extraLarge,
            maxLines = 1,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
                autoCorrect = true
            ),
            keyboardActions = KeyboardActions(onSearch = {
                focusManager.clearFocus()
                keyboardController?.hide()
            })
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TextFieldPreview() {
    VixitaskTheme {
        TextFieldForm(value = "", onChange = {}, modifier = Modifier.fillMaxWidth())
    }
}