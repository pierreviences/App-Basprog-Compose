package com.example.basprogapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserInfo(text: String, fontSize: Int, spacer:Int, modifier: Modifier = Modifier) {
    Spacer(modifier = Modifier.height(spacer.dp))
    Text(
        text = text,
        fontSize = fontSize.sp,
        modifier = modifier
    )
}
