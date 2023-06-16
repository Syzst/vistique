package com.bangkitcapstone.vistique.ui.screen.detailshop

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.Shop
import com.bangkitcapstone.vistique.model.dummyBatik
import com.bangkitcapstone.vistique.model.dummyShop
import com.bangkitcapstone.vistique.ui.components.MyRecItem
import com.bangkitcapstone.vistique.ui.components.MyRecShopItem
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun DetailShopScreen(
    shop: Shop,
    modifier: Modifier = Modifier
){
    val context = (LocalContext.current as? Activity)

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(shop.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = shop.title,
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
                    text = shop.price,
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
                    text = shop.desc,
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Justify,
                    color = Color.Black,
                    modifier = modifier
                        .fillMaxWidth()
                        .heightIn(70.dp)
                        .padding(horizontal = 16.dp)
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.TopCenter,
                ){
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
                        MySection(
                            title = "View More",
                            content = { RecShopRow(listShop = dummyShop, ids = shop.id) },
                        )
                    }
                }
            }
        }
        IconButton(
            modifier = Modifier
                .padding(16.dp)
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
fun DetailShopScreenPreview(){
    VistiqueTheme {
        DetailShopScreen(shop = dummyShop.get(0))
    }
}

@Composable
fun RecShopRow(
    listShop: List<Shop>,
    ids: Int,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listShop, key = {it.id}){shop ->
            val moveIntent = Intent(context, DetailShopActivity::class.java)
            val id = shop.id-1
            moveIntent.putExtra(DetailShopActivity.EXTRA_ID, id.toString())
            MyRecShopItem(shop = shop, onClick = {
                if(shop.id != ids){
                    context.startActivity(Intent(moveIntent))
                    context.finish()
                }else{

                }
            })
        }
    }
}