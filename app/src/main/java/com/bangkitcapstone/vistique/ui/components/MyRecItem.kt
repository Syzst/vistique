package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.ResponseItem
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MyRecItem(
    batik: ResponseItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .width(90.dp)
            .height(120.dp)
            .clickable(
                onClick = onClick
            )
    ) {
        Column() {
            GlideImage(
                model = batik.pbatikPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .clip(Shapes.medium)
            )
//            Image(
//                painter = painterResource(id = batik.image),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(70.dp)
//                    .clip(Shapes.medium)
//            )
            Column(
                modifier = modifier
                    .padding(top = 4.dp)
            ) {
                Text(
                    text = batik.pbatikName,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = batik.pbatikHistory,
                    maxLines = 1,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.primary,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyRecItemPreview(){
    VistiqueTheme {
//        MyRecItem(
//            batik = Batik(1, R.drawable.batik_1, "Batik Parang", "Yogyakarta", "Coba"), {}
//        )
    }
}