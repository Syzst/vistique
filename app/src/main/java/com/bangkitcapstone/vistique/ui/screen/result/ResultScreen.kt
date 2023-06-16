package com.bangkitcapstone.vistique.ui.screen.result

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.dummyBatik
//import com.bangkitcapstone.vistique.ui.screen.ar.ARActivity
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun ResultScreen(
    batik: Batik,
    modifier: Modifier = Modifier
){
    val context = (LocalContext.current as? Activity)

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(batik.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = batik.title,
                        maxLines = 1,
                        style = MaterialTheme.typography.h5,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = batik.subtitle,
                        maxLines = 1,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = batik.desc,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify,
                        color = Color.Black,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                }
                Column(
                    modifier = modifier
                        .align(Alignment.BottomCenter)
                ) {
                    Spacer(modifier = modifier.height(16.dp))
                    Box(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(Color.Black)
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Button(
                        onClick = { /*context?.startActivity(Intent(context, ARActivity::class.java) )*/ },
                        shape = Shapes.small,
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.primary,
                            contentColor = Color.White
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .heightIn(min = 48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CameraAlt,
                            contentDescription = null,
                            tint = Color.White
                        )
                        Text(text = "Try AR Feature",style = MaterialTheme.typography.button, color = Color.White, modifier = Modifier.padding(start = 8.dp))
                    }
                }
            }
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.TopStart),
            onClick = { context?.finish() }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardBackspace,
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview(){
    VistiqueTheme {
        ResultScreen(batik = dummyBatik.get(0))
    }
}