package com.vixiloc.vixitask.presentations.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vixiloc.vixitask.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskActiveCard(
    title: String,
    dateTime: String,
    onClick: () -> Unit = {},
    onDone: () -> Unit = {},
    onShare: () -> Unit = {},
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(color = MaterialTheme.colorScheme.tertiary)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(
                text = dateTime,
                color = Color.Black,
                style = MaterialTheme.typography.bodySmall
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Button(
                        onClick = { onDone() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onTertiary,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.done),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .width(110.dp)
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onDelete() },
                        modifier = Modifier,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Filled.DeleteForever,
                            contentDescription = "Delete",
                        )
                    }

                    IconButton(
                        onClick = { onShare() },
                        modifier = Modifier,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            modifier = Modifier.size(30.dp),
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share",
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskInactiveCard(
    title: String,
    dateTime: String,
    onClick: () -> Unit = {},
    onShare: () -> Unit = {},
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(shape = MaterialTheme.shapes.extraLarge)
            .background(color = MaterialTheme.colorScheme.secondary)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = title,
                color = Color.Black,
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )

            Text(text = dateTime, color = Color.Black, style = MaterialTheme.typography.bodySmall)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Row(
                        modifier = Modifier
                            .width(110.dp)
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(
                            onClick = { onDelete() },
                            modifier = Modifier,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.DeleteForever,
                                contentDescription = "Delete",
                            )
                        }

                        IconButton(
                            onClick = { onShare() },
                            modifier = Modifier,
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(30.dp),
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share",
                            )
                        }
                    }
                }
            }
        }
    }
}