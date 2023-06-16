package com.bangkitcapstone.vistique.model

import com.bangkitcapstone.vistique.R

data class Article(
    val id: Int,
    val image: Int,
    val title: String
)

val dummyArticle = listOf(
    Article(1, R.drawable.berita_1, "The Making of Batik"),
    Article(2, R.drawable.berita_1, "The Making of Batik"),
    Article(3, R.drawable.berita_1, "The Making of Batik"),
    Article(4, R.drawable.berita_1, "The Making of Batik"),
)