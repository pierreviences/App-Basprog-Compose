package com.example.basprogapp.ui.screen.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.basprogapp.R
import com.example.basprogapp.di.Injection
import com.example.basprogapp.ui.ViewModelFactory
import com.example.basprogapp.ui.common.UiState
import com.example.basprogapp.ui.components.Header
import com.example.basprogapp.ui.components.InfoDetail
import com.example.basprogapp.ui.theme.BasprogAppTheme

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateBack: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { state ->
        when (state) {
            is UiState.Loading -> {
                viewModel.getBasprogById(id)
            }
            is UiState.Success -> {
                val data = state.data
                DetailContent(
                    name = data.name,
                    description = data.description,
                    like = data.like,
                    creator = data.creator,
                    years = data.creator,
                    photoUrl =  data.photoUrl,
                    isFavorite = data.isFavorite,
                    onBackClick = navigateBack,
                    onFavClick = {
                        viewModel.addToFavorite(data.id, !data.isFavorite)
                    }
                )
            }
            is UiState.Error -> {

            }
        }
    }
}

@Composable
fun DetailContent(
    name: String,
    description: String,
    like: String,
    creator: String,
    years: String,
    photoUrl: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onFavClick: () -> Unit,
    modifier: Modifier = Modifier
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header(name = stringResource(R.string.detail), onBackClick = onBackClick, modifier = modifier)
        Row(
            modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(
                            bottom = 10.dp
                        )
                )
                InfoDetail(icon = Icons.Default.Favorite, text = "${like} % ${stringResource(R.string.favorite_developer)}")
                InfoDetail(icon = Icons.Default.AccountCircle, text =   "${stringResource(R.string.creator)} ${creator}")
                InfoDetail(icon = Icons.Default.DateRange, text =  "${stringResource(R.string.years)} ${years}")

            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.deskripsi),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = modifier
                    .padding(
                        top = 3.dp
                    )
            )
            IconButton(
                onClick = {
                    onFavClick()
                },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite),
                    tint = Color.Red
                )
            }
        }
        Text(
            text = description,
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview(){
    BasprogAppTheme {
        DetailContent(
            name = "Python",
            description = "JavaScript is a programming language commonly used for web development. It enables developers to create dynamic interactions on websites, changing page content in real-time, and providing interactive experiences to users.",
            like = "85",
            creator = "John Doe",
            years = "2022",
            photoUrl = "your_photo_url_here",
            isFavorite = false,
            onBackClick = {},
            onFavClick = {}
        )
    }
}

