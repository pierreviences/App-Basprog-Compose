package com.example.basprogapp.ui.components.card

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CardImage(photoUrl: String,  name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = photoUrl,
        contentDescription = name,
        modifier = modifier
            .size(100.dp)
            .padding(8.dp)
            .fillMaxHeight(),
        contentScale = ContentScale.FillWidth
    )
}
