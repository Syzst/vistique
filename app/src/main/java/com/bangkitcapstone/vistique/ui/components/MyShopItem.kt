package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.Shop
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun MyShopItem(
    shop: Shop,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        shape = Shapes.medium,
        modifier = modifier
            .width(150.dp)
            .height(225.dp)
            .shadow(elevation = 4.dp, shape = Shapes.medium)
            .clickable(
                onClick = onClick
            )
    ) {
        Column() {
            Image(
                painter = painterResource(id = shop.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )
            Column(
                modifier = modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = shop.title,
                    maxLines = 2,
                    style = MaterialTheme.typography.h1,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = shop.price,
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
fun MyShopItemPreview(){
    VistiqueTheme {
        MyShopItem(
            shop = Shop(1, R.drawable.batik_1, "Batik Parang Batik Bagus Batik Parang", "Rp. 200.000", ""), {}
        )
    }
}