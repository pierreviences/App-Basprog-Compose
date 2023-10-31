package com.example.basprogapp.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basprogapp.ViewModelFactory
import com.example.basprogapp.data.BasprogRepository
import com.example.basprogapp.model.BasprogData
import com.example.basprogapp.ui.components.BasprogCard
import com.example.basprogapp.ui.components.SearchBar
import com.example.basprogapp.ui.theme.BasprogAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(BasprogRepository()))
) {
    val query by viewModel.query
    Box(modifier = modifier) {
        LazyColumn {
            item {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchBasprog,
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                )

            }
            items(BasprogData.basprogs, key = { it.id }) {
                BasprogCard(
                    index = it.id,
                    name = it.name,
                    description = it.description,
                    photoUrl = it.photoUrl,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .animateItemPlacement(tween(durationMillis = 100))
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