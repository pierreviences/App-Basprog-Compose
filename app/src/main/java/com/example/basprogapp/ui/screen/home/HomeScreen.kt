package com.example.basprogapp.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.basprogapp.model.BasprogData
import com.example.basprogapp.ui.components.BasprogCard
import com.example.basprogapp.ui.theme.BasprogAppTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(BasprogData.basprogs, key = { it.id }) {
                BasprogCard(
                    index = it.id,
                    name = it.name,
                    description = it.description,
                    photoUrl = it.photoUrl,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    BasprogAppTheme {
        HomeScreen()
    }
}