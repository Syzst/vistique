package com.bangkitcapstone.vistique.ui.screen.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.model.Shop
import com.bangkitcapstone.vistique.model.dummyShop
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.components.MyShopItem
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings

@Composable
fun StoreScreen(
    modifier: Modifier = Modifier
){
    MySection(title = "Nearest Batik Store") {
        StoreMaps()
    }
}

@Composable
fun StoreMaps(
    modifier: Modifier = Modifier
) {
    GoogleMap(
        modifier = modifier
            .fillMaxSize(),
        uiSettings = MapUiSettings(
            compassEnabled = true,
            zoomControlsEnabled = true
        ),
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(LocalContext.current, R.raw.map_style),
            isMyLocationEnabled = true
        ),
    )
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun StoreMapsPreview(){
    VistiqueTheme {
        StoreMaps()
    }
}