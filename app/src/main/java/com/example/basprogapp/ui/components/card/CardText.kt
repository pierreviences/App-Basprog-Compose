package com.example.basprogapp.ui.components.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

@Composable
fun CardText(index: Int, name: String, description: String) {
    Column {
        CardTitle(index = index, name = name)
        CardDescription(description = description)
    }
}