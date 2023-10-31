package com.example.basprogapp.ui.screen.home

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.basprogapp.R
import com.example.basprogapp.ui.ViewModelFactory
import com.example.basprogapp.di.Injection
import com.example.basprogapp.model.Basprog
import com.example.basprogapp.ui.common.UiState
import com.example.basprogapp.ui.components.BasprogCard
import com.example.basprogapp.ui.components.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = ViewModelFactory(Injection.provideRepository())),
    navigateToDetail: (String) -> Unit
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getBasprog()
            }
            is UiState.Success -> {
                HomeContent(
                    query = query,
                    onQueryChange = viewModel::searchBasprog,
                    listBasprog = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> {}
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    query: String,
    onQueryChange: (String) -> Unit,
    listBasprog: List<Basprog>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
) {
    val listState = rememberLazyListState()
    Scaffold(
        modifier
            .fillMaxSize(),
        topBar = {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange
            )
        },
    ){innerPadding ->
        LazyColumn(
            state = listState,
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ){
            if (listBasprog.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.data_not_avaible),
                            fontSize = 18.sp
                        )
                    }
                }
            }else {
                items(listBasprog, key = { it.id }) {
                    BasprogCard(
                        index = it.id,
                        name = it.name,
                        description = it.description,
                        photoUrl = it.photoUrl,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigateToDetail(it.id)
                                }
                                .animateItemPlacement(tween(durationMillis = 100))
                    )
            }

            }
        }

    }
}

