package com.bangkitcapstone.vistique.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("ArticleResponse")
	val articleResponse: List<ArticleResponseItem>
)

data class ArticleResponseItem(

	@field:SerializedName("article_title")
	val articleTitle: String,

	@field:SerializedName("article_description")
	val articleDescription: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("article_photo")
	val articlePhoto: String,

	@field:SerializedName("article_link")
	val articleLink: String,
)
