package com.bangkitcapstone.vistique.ui.screen.explore

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.ArticleResponseItem
import com.bangkitcapstone.vistique.model.Batik
import com.bangkitcapstone.vistique.model.ResponseItem
import com.bangkitcapstone.vistique.model.dummyBatik
import com.bangkitcapstone.vistique.service.ApiConfig
import com.bangkitcapstone.vistique.ui.components.MyItem
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current as Activity

    var listBatik by remember {
        mutableStateOf<List<ResponseItem>>(listOf())
    }

    fun findBatik() {
        val client = ApiConfig.getApiService().getAllBatik()
        client.enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: Response<List<ResponseItem>>
            ){
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        listBatik = responseBody
                    }
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    findBatik()

    MySection(
        title = "Batik's List",
        content = { BatikVerticalGrid(listBatik = listBatik) }
    )
}

@Composable
fun BatikVerticalGrid(
    listBatik: List<ResponseItem>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyVerticalGrid(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(2)
    ) {
        items(listBatik, key = {it.id}){batik ->
            val moveIntent = Intent(context, DetailBatikActivity::class.java)
            val id = batik.id
            moveIntent.putExtra(DetailBatikActivity.EXTRA_ID, id)
            Box() {
                MyItem(batik = batik, modifier = modifier.fillMaxWidth(), onClick = {context.startActivity(
                    Intent(moveIntent)
                )})
                IconButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 60.dp, end = 4.dp),
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                    )
                }
            }
        }
    }
}