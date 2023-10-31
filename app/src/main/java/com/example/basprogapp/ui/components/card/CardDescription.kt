package com.example.basprogapp.ui.components.card

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardDescription(description: String) {
    Text(
        text = description,
        color = Color.Black,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
    )
}