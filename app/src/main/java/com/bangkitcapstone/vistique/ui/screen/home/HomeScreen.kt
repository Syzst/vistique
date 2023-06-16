package com.bangkitcapstone.vistique

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import androidx.compose.ui.tooling.preview.Preview
import com.bangkitcapstone.vistique.ui.components.MyArticleItem
import com.bangkitcapstone.vistique.ui.components.MyItem
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import com.bangkitcapstone.vistique.model.*
import com.bangkitcapstone.vistique.service.ApiConfig
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.screen.article.ArticleActivity
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current as Activity

    var listArticle by remember {
        mutableStateOf<List<ArticleResponseItem>>(listOf())
    }

    var listBatik by remember {
        mutableStateOf<List<ResponseItem>>(listOf())
    }

    fun findArticle() {
        val client = ApiConfig.getApiService().getAllArticles()
        client.enqueue(object : Callback<List<ArticleResponseItem>> {
            override fun onResponse(
                call: Call<List<ArticleResponseItem>>,
                response: Response<List<ArticleResponseItem>>
            ){
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        listArticle = responseBody
                    }
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ArticleResponseItem>>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
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
    findArticle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        MySection(
            title = "Most Popular",
            content = { BatikHorizontalGrid(listBatik = listBatik) }
        )
        Spacer(modifier = Modifier.height(16.dp))
        MySection(
            title = "Article",
            content = { ArticleRow(listArticle = listArticle) }
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview(){
    VistiqueTheme {
        HomeScreen()
    }
}

@Composable
fun ArticleRow(
    listArticle: List<ArticleResponseItem>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listArticle, key = {it.id}){article ->
            val moveIntent = Intent(context, ArticleActivity::class.java)
            val id = article.id
            moveIntent.putExtra(DetailBatikActivity.EXTRA_ID, id)
            MyArticleItem(article = article) { context.startActivity(moveIntent) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleRowPreview(){
    VistiqueTheme {
//        ArticleRow(dummyArticle)
    }
}

@Composable
fun BatikHorizontalGrid(
    listBatik: List<ResponseItem>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyHorizontalGrid(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .height(314.dp)
            .padding(horizontal = 16.dp),
        rows = GridCells.Fixed(2)
    ) {
       items(listBatik.take(4), key = {it.id}){batik ->
           val moveIntent = Intent(context, DetailBatikActivity::class.java)
           val id = batik.id
           moveIntent.putExtra(DetailBatikActivity.EXTRA_ID, id)
           Box() {
               MyItem(batik = batik, onClick = {context.startActivity(
                   Intent(moveIntent)
               )}, modifier = modifier.fillMaxWidth())
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

@Preview(showBackground = true)
@Composable
fun BatikGridPreview(){
    VistiqueTheme {
//        BatikHorizontalGrid(dummyBatik)
    }
}