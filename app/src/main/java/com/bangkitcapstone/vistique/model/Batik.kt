package com.bangkitcapstone.vistique.model

import com.bangkitcapstone.vistique.R

data class Batik(
    val id: Int,
    val image: Int,
    val title: String,
    val subtitle: String,
    val desc: String
)

data class Jenis(
    val id: Int,
    val title: String,
    val subtitle: String,
    val desc: String
)

val dummyBatik = listOf(
    Batik(1, R.drawable.batik_1, "Batik Parang", "Yogyakata", "Batik Parang adalah salah satu motif batik yang paling tua di Indonesia. Parang berasal dari kata Pereng yang berarti lereng. Perengan menggambarkan sebuah garis menurun dari tinggi ke rendah secara diagonal. Susunan motif S jalin-menjalin tidak terputus melambangkan kesinambungan. Bentuk dasar huruf S diambil dari ombak samudra yang menggambarkan semangat yang tidak pernah padam. Batik ini merupakan batik asli Indonesia yang sudah ada sejak zaman keraton Mataram Kartasura (Solo)."),
    Batik(2, R.drawable.batik_2, "Batik Megamendung", "Cirebon", "Batik Megamendung merupakan karya seni batik yang identik dan bahkan menjadi ikon batik daerah Cirebon dan daerah Indonesia lainnya. Motif batik ini mempunyai kekhasan yang tidak ditemui di daerah penghasil batik lain."),
    Batik(3, R.drawable.batik_3, "Batik Kawung", "Yogyakata", "Batik Kawung adalah motif batik yang bentuknya berupa bulatan mirip buah kawung yang ditata rapi secara geometris. Kadang, motif ini juga ditafsirkan sebagai gambar bunga lotus dengan empat lembar mahkota bunga yang merekah. Lotus adalah bunga yang melambangkan umur panjang dan kesucian."),
    Batik(4, R.drawable.batik_4, "Batik Keraton", "Yogyakata", "Batik keraton adalah batik yang berkembang dalam lingkungan keraton, baik Yogyakarta maupun Surakarta. Batik keraton merupakan awal mula dari semua jenis batik yang berkembang di Indonesia. Motifnya mengandung beragam makna filosofi hidup yang banyak terilhami dari kebudayaan Hindu-Jawa."),
    Batik(5, R.drawable.batik_5, "Batik Besurek", "Bengkulu", "Batik Besurek adalah batik khas Bengkulu yang bermotif kaligrafi Arab. Pada umumnya, batik ini berciri khas motif kaligrafi dengan perpaduan bunga Rafflesia yang merupakan simbol khas Bengkulu."),
    Batik(6, R.drawable.batik_6, "Batik Cuwiri", "Solo", "Batik Cuwiri meruapakan motif batik yang menggunakan zat pewarna soga alam. Biasanya batik ini digunakan untuk semekan dan kemben, juga digunakan pada saat upacara mitoni. Motif batik ini kebanyakan menggunakan unsur meru dan gurda."),
    Batik(7, R.drawable.batik_7, "Batik Gedog", "Tuban", "Batik Gedog adalah batik khas masyarakat Kabupaten Tuban. Kainnya dibuat dengan cara ditenun. Motifnya berbentuk kotak-kotak atau garis hitam putih."),
    Batik(8, R.drawable.batik_8, "Batik Pringgondani", "Jawa", "Batik Pringgondani adalah salah satu motif batik Jawa, Indonesia. Pringgondani atau pringgodani adalah nama sasana kesatriyan tempat tinggal Gatotkaca putra Werkudara. Corak ini umumnya dirancang dalam warna-warna gelap seperti biru nila dan soga-coklat."),
    Batik(9, R.drawable.batik_9, "Batik Bali", "Bali", "Batik Bali adalah hasil penyebaran Batik dari Pulau Jawa. Bali mempunyai potensi yang besar sebagai tempat bertumbuh dan berkembangnya batik, karena masyarakat Bali diketahui secara luas mempunyai kepandaian yang tinggi dalam olah seni."),
    Batik(10, R.drawable.batik_10, "Batik Madura", "Madura", "Batik Madura adalah batik khas Pulau Madura yang memiliki beragam motif dan corak yang unik. Motifnya dibuat secara tradisional dan menggunakan pewarna alami serta diwariskan secara turun-temurun."),
)

val jenisBatik = listOf(
    Jenis(1, "Batik Ikat Celup", "Jawa", "Ikat celup atau Jumputan (tie-dye) adalah teknik mewarnai kain dengan cara mengikat kain dengan cara tertentu sebelum dilakukan pencelupan. Di beberapa daerah di Indonesia, teknik ini dikenal dengan berbagai nama lain seperti jumputan pelangi atau cinde (Palembang), tritik atau jumputan (Jawa), serta sasirangan (Banjarmasin). Teknik ikat celup sering dipadukan dengan teknik lain seperti batik."),
    Jenis(2, "Batik Insang", "Pontianak", "Tenun Corak Insang adalah tenunan tradisional khas masyarakat suku Melayu di Kota Pontianak. Tenunan ini dikenal sejak masa Kesultanan Kadriah di bawah kekuasaan Sultan Syarif Abdurrahman Al Qadrie tahun 1771 hingga saat ini. Awalnya Corak Insang hanya digunakan oleh kaum bangsawan di Istana Kadriah. Fungsi tenun Corak Insang adalah sebagai penunjuk identitas status sosial bagi satu keluarga atau satu kelompok dalam kehidupan bermasyarakat dan saat diadakannya pertemuan antar kerajaan."),
    Jenis(3, "Batik Kawung", "Yogyakata", "Batik Kawung adalah motif batik yang bentuknya berupa bulatan mirip buah kawung yang ditata rapi secara geometris. Kadang, motif ini juga ditafsirkan sebagai gambar bunga lotus dengan empat lembar mahkota bunga yang merekah. Lotus adalah bunga yang melambangkan umur panjang dan kesucian."),
    Jenis(4, "Batik Megamendung", "Cirebon", "Batik Megamendung merupakan karya seni batik yang identik dan bahkan menjadi ikon batik daerah Cirebon dan daerah Indonesia lainnya. Motif batik ini mempunyai kekhasan yang tidak ditemui di daerah penghasil batik lain."),
    Jenis(5, "Batik Parang", "Yogyakata", "Batik Parang adalah salah satu motif batik yang paling tua di Indonesia. Parang berasal dari kata Pereng yang berarti lereng. Perengan menggambarkan sebuah garis menurun dari tinggi ke rendah secara diagonal. Susunan motif S jalin-menjalin tidak terputus melambangkan kesinambungan. Bentuk dasar huruf S diambil dari ombak samudra yang menggambarkan semangat yang tidak pernah padam. Batik ini merupakan batik asli Indonesia yang sudah ada sejak zaman keraton Mataram Kartasura (Solo)."),
)