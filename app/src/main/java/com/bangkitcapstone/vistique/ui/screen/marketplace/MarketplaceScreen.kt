package com.bangkitcapstone.vistique.ui.screen.marketplace

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.Shop
import com.bangkitcapstone.vistique.model.dummyShop
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.components.MyShopItem
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import com.bangkitcapstone.vistique.ui.screen.detailshop.DetailShopActivity

@Composable
fun MarketplaceScreen(
    modifier: Modifier = Modifier
){
    MySection(
        title = "Today's Drop",
        content = { ShopVerticalGrid(listShop = dummyShop) }
    )
}

@Composable
fun ShopVerticalGrid(
    listShop: List<Shop>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(listShop, key = {it.id}){shop ->
            val moveIntent = Intent(context, DetailShopActivity::class.java)
            val id = shop.id-1
            moveIntent.putExtra(DetailShopActivity.EXTRA_ID, id.toString())
            MyShopItem(shop = shop, modifier = modifier.fillMaxWidth(), onClick = {context.startActivity(
                Intent(moveIntent)
            )})
        }
    }
}