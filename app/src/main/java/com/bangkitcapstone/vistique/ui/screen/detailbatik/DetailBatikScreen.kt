package com.bangkitcapstone.vistique.ui.screen.detailbatik

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.*
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
import com.bangkitcapstone.vistique.ArticleRow
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.model.*
import com.bangkitcapstone.vistique.service.ApiConfig
import com.bangkitcapstone.vistique.ui.components.*
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DetailBatikScreen(
    id: String,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current as Activity

    var listBatik by remember {
        mutableStateOf<List<ResponseItem>>(listOf())
    }

    var batik by remember {
        mutableStateOf<ResponseItem>(ResponseItem("","","","","",""))
    }

    fun findAllBatik() {
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

    fun findBatik() {
        val client = ApiConfig.getApiService().getBatik(id)
        client.enqueue(object : Callback<ResponseItem> {
            override fun onResponse(call: Call<ResponseItem>, response: Response<ResponseItem>) {
                if(response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody != null){
                        batik = responseBody
                    }
                }else{
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseItem>, t: Throwable) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    findBatik()
    findAllBatik()

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
                model = batik.pbatikPhoto,
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
                        text = batik.pbatikName,
                        maxLines = 1,
                        style = MaterialTheme.typography.h4,
                        color = Color.Black,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = batik.pbatikDescription,
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Justify,
                        color = Color.Black,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = modifier.height(16.dp))
                    Text(
                        text = "Motif History",
                        style = MaterialTheme.typography.h5,
                        color = Color.Black,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = batik.pbatikHistory,
                        style = MaterialTheme.typography.h1,
                        color = MaterialTheme.colors.primary,
                        modifier = modifier
                            .fillMaxWidth()
                            .heightIn(260.dp)
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
                                content = { RecBatikRow(listBatik = listBatik, batik.id) },
                            )
                        }
                    }
                }
                IconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
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