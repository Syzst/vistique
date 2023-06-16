package com.bangkitcapstone.vistique.service

import com.bangkitcapstone.vistique.model.ArticleResponse
import com.bangkitcapstone.vistique.model.ArticleResponseItem
import com.bangkitcapstone.vistique.model.BatikResponse
import com.bangkitcapstone.vistique.model.ResponseItem
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("pbatiks")
    fun getAllBatik(): Call<List<ResponseItem>>

    @GET("articles")
    fun getAllArticles(): Call<List<ArticleResponseItem>>

    @GET("pbatik/{id}")
    fun getBatik(
        @Path("id") id: String
    ): Call<ResponseItem>

    @GET("article/{id}")
    fun getArticle(
        @Path("id") id: String
    ): Call<ArticleResponseItem>
}