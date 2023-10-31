package com.example.basprogapp.ui.screen.profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.basprogapp.R
import com.example.basprogapp.ui.theme.BasprogAppTheme
import com.example.basprogapp.ui.theme.Primary80

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .fillMaxSize()
        ){
        Box(modifier = Modifier
            .background(Primary80)
            .fillMaxWidth()
            .height(200.dp),
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(110.dp))
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(R.string.img_profile),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(180.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.name),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = modifier
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.email),
                fontSize = 14.sp,
                modifier = modifier
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = stringResource(R.string.place),
                fontSize = 14.sp,
                modifier = modifier
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    BasprogAppTheme {
        ProfileScreen()
    }
}