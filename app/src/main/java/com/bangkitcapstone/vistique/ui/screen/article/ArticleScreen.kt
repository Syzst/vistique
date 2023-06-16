package com.bangkitcapstone.vistique.ui.screen.article

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.model.ArticleResponseItem
import com.bangkitcapstone.vistique.model.ResponseItem
import com.bangkitcapstone.vistique.service.ApiConfig
import com.bangkitcapstone.vistique.ui.components.MyRecItem
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ArticleScreen(
    id: String,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current as Activity

    var listBatik by remember {
        mutableStateOf<List<ResponseItem>>(listOf())
    }

    var article by remember {
        mutableStateOf<ArticleResponseItem>(ArticleResponseItem("","","","",""))
    }

    fun findBatik() {
        val client = ApiConfig.getApiService().getArticle(id)
        client.enqueue(object : Callback<ArticleResponseItem> {
            override fun onResponse(call: Call<ArticleResponseItem>, response: Response<ArticleResponseItem>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        article = responseBody
                    }
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArticleResponseItem>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    findBatik()

    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GlideImage(
                model = article.articlePhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
//            Image(
//                painter = painterResource(batik.image),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(250.dp)
//            )
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopCenter,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = article.articleTitle,
                        style = MaterialTheme.typography.h4,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = article.articleDescription,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify,
                        color = Color.Black,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = article.articleLink,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
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
fun DetailBatikScreenPreview(){
    VistiqueTheme {
//        DetailBatikScreen(batik = dummyBatik.get(0))
    }
}

@Composable
fun RecBatikRow(
    listBatik: List<ResponseItem>,
    ids: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current as Activity

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listBatik, key = {it.id}){batik ->
            val moveIntent = Intent(context, DetailBatikActivity::class.java)
            val id = batik.id
            moveIntent.putExtra(DetailBatikActivity.EXTRA_ID, id)
            MyRecItem(batik = batik, onClick = {
                if(batik.id != ids){
                    context.startActivity(Intent(moveIntent))
                    context.finish()
                }else{

                }
            })
        }
    }
}