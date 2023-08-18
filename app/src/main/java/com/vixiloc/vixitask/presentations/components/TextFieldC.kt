package com.vixiloc.vixitask.presentations.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDefault(
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
        TextField(
            value = value,
            onValueChange = { onChange(it) },
            leadingIcon = leadingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .padding(vertical = 5.dp),
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                textColor = Color.Black,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.extraLarge,
            maxLines = 5,
            readOnly = readOnly
        )
    }
}