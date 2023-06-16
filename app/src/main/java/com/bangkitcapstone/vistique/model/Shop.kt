package com.bangkitcapstone.vistique.model

import com.bangkitcapstone.vistique.R

data class Shop(
    val id: Int,
    val image: Int,
    val title: String,
    val price: String,
    val desc: String
)

val dummyShop = listOf(
    Shop(1, R.drawable.shop_1, "Tas", "Rp. 50.000 - Rp. 100.000", "Tas batik adalah salah satu produk paling diminati di pasar lokal, produk kreatif ini biasa diproduksi di sentra kerajinan batik di Solo maupun Yogyakarta. Ada berbagai macam tas yang diproduksi, mulai tas ransel, tas wanita, hingga anak-anak bahkan tas gadget. semuanya bermotif batik."),
    Shop(2, R.drawable.shop_2, "Dompet", "Rp. 40.000 - Rp. 70.000", "Dompet batik juga merupakan salah satu produk kreatif yang terus berkembang menyesuaikan selera pasar. Selain itu di beberapa sentra batik di Indonesia, dompet batik dapat dipesan sesuai selera. hal inilah yang menyebabkan terus berkembangnya industri domper batik."),
    Shop(3, R.drawable.shop_3, "Topi", "Rp. 30.000 - Rp. 60.000", "Topi juga memiliki kreasi dengan desain batik, tidak hanya topi biasa. Topi tipe snapback yang biasa digunakan para rapper juga memiliki desain motif batik salah satunya."),
    Shop(4, R.drawable.shop_4, "Sepatu", "Rp. 100.000 - Rp. 200.000", "Sepatu juga tidak lepas dari tangan kreatif para pengusaha batik, kreasi-kreasi kreatif batik membuat sepatu menjadi istimewa dan diminati."),
    Shop(5, R.drawable.shop_5, "Dress", "Rp. 70.000 - Rp. 100.000", "Dunia fashion semakin beragam, semakin banyak pula kreasi yang dibentuknya. hal ini menuntut para produsen memiliki kreasi batik untuk mewarnai dress desain mereka."),
    Shop(6, R.drawable.shop_6, "Jaket", "Rp. 80.000 - Rp. 150.000", "Tak mau ketinggalan dunia fashion pria juga memiliki inovasi dalam desain pakaiannya, salah satunya jaket yang bermotif batik."),
    Shop(7, R.drawable.shop_7, "Kemeja Bola", "Rp. 50.000 - Rp. 100.000", "Penggabungan desain modern dan tradisonal batik, ternyata mampu diterima pasar oleh para penggemar bola, produk tersebut ialah kemeja bola batik."),
    Shop(8, R.drawable.shop_8, "Selendang", "Rp. 20.000 - Rp. 50.000", "Untuk melegkapi fasihon batik yang semakin beragam, dibuatlah juga pelengkapnya, seperti selendang batik."),
    Shop(9, R.drawable.shop_9, "Sprei", "Rp. 100.000 - Rp. 100.000", "Selain itu, motif batik yang unik dan lembut di mata membuat motif batik sebagai salah satu pilihan dalam menghias rumah salah satunya. sprei dan sarung bantal."),
    Shop(10, R.drawable.shop_10, "Jepit Rambut", "Rp. 10.000 - Rp. 20.000", "Batik juga merambah pada aksesoris-aksesoris unik yang digunakan sehari-hari. seperti, sisir, penjepit rambut, dll."),
)