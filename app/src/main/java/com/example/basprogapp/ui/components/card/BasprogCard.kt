package com.example.basprogapp.ui.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.basprogapp.ui.theme.BasprogAppTheme

@Composable
fun BasprogCard(
    index: String,
    name: String,
    description: String,
    photoUrl: String,
    modifier: Modifier = Modifier
){
    val indexInt = index.toInt()
    Card(
        modifier
            .padding(
                start = 10.dp,
                top = 10.dp,
                end = 10.dp,
                bottom = 0.dp
            )
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CardImage(photoUrl = photoUrl, name = name)
            CardText(index = indexInt, name = name, description = description)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BasprogCardPreview(){
    BasprogAppTheme {
        BasprogCard(
            index = "1",
            name = "Python",
            description = "JavaScript is a programming language commonly used for web development. It enables developers to create dynamic interactions on websites, changing page content in real-time, and providing interactive experiences to users.",
            photoUrl = "")
    }
}