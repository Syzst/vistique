package com.bangkitcapstone.vistique.ui.screen.favorite

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.dummyBatik
import com.bangkitcapstone.vistique.ui.components.MyItem
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
){
//    val context = (LocalContext.current as? Activity)
//
//    Box(
//        contentAlignment = Alignment.TopCenter,
//        modifier = modifier
//            .fillMaxSize()
//    ){
//        Column(){
//            FavoritedBatikVerticalGrid(listBatik = dummyBatik)
//        }
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = modifier
//                .padding(16.dp)
//        ) {
//            Text(
//                text = "My Favorites Batik",
//                textAlign = TextAlign.Center,
//                style = MaterialTheme.typography.h3,
//                color = Color.Black,
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//        }
//        IconButton(
//            modifier = Modifier
//                .align(Alignment.TopStart),
//            onClick = { context?.finish() }
//        ) {
//            Icon(
//                imageVector = Icons.Default.KeyboardBackspace,
//                contentDescription = null,
//                tint = MaterialTheme.colors.primary,
//            )
//        }
//    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun FavoriteScreenPreview(){
    VistiqueTheme {
        FavoriteScreen()
    }
}

//@Composable
//fun FavoritedBatikVerticalGrid(
//    listBatik: List<Batik>,
//    modifier: Modifier = Modifier
//) {
//    val context = (LocalContext.current as? Activity)
//
//    LazyVerticalGrid(
//        verticalArrangement = Arrangement.spacedBy(18.dp),
//        horizontalArrangement = Arrangement.spacedBy(18.dp),
//        contentPadding = PaddingValues(horizontal = 22.dp),
//        modifier = modifier
//            .fillMaxSize(),
//        columns = GridCells.Fixed(2),
//    ) {
//        items(listBatik, key = {it.id}){batik ->
//            Box() {
//                MyItem(batik = batik, onClick = {context?.startActivity(
//                    Intent(context, DetailBatikActivity::class.java).putExtra(DetailBatikActivity.EXTRA_ID, batik.id)
//                )})
//                IconButton(
//                    modifier = Modifier
//                        .align(Alignment.BottomEnd)
//                        .padding(bottom = 62.dp),
//                    onClick = {  }
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Delete,
//                        contentDescription = null,
//                        tint = MaterialTheme.colors.secondaryVariant,
//                        modifier = modifier
//                            .background(Color.White, Shapes.small)
//                    )
//                }
//            }
//        }
//    }
//}