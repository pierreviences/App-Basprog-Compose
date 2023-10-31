package com.example.basprogapp.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
import com.example.basprogapp.di.Injection
import com.example.basprogapp.ui.ViewModelFactory
import com.example.basprogapp.ui.common.UiState
import com.example.basprogapp.ui.components.BasprogCard
import com.example.basprogapp.ui.theme.BasprogAppTheme
import com.example.basprogapp.ui.theme.Primary80

@Composable
fun DetailScreen(
    id: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    )
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
    onFavClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = name,
                modifier = Modifier
                    .size(90.dp)
                    .fillMaxHeight()
                    .padding(
                        top = 5.dp
                    ),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Primary80
                    )

                    Text(
                        text = "${like} % developer stack overflow",
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        tint = Primary80
                    )

                    Text(
                        text = "diciptkan oleh ${creator}",
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Primary80
                    )

                    Text(
                        text = "dirilis pada tahun ${years}",
                        color = Color.Black,
                        fontSize = 12.sp,
                    )
                }

            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Deskripsi",
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
                    contentDescription = "asdasd",
                    tint = Color.Red
                )
            }
        }
        Text(
            text = description,
            color = Color.Black,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
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
            onFavClick = {}
        )
    }
}

