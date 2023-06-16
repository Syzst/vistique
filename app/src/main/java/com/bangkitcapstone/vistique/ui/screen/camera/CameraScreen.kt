package com.bangkitcapstone.vistique.ui.screen.camera

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.view.PixelCopy
import android.view.RoundedCorner
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.bangkitcapstone.vistique.BuildConfig
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.ml.BatikDatasets
import com.bangkitcapstone.vistique.model.Jenis
import com.bangkitcapstone.vistique.model.jenisBatik
import com.bangkitcapstone.vistique.model.uriToFile
import com.bangkitcapstone.vistique.ui.components.MySection
import com.bangkitcapstone.vistique.ui.components.MyTextButton
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikActivity
import com.bangkitcapstone.vistique.ui.screen.register.RegisterActivity
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraScreen(
    modifier: Modifier = Modifier,
){
    val context = LocalContext.current as Activity

    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID + ".provider", file
    )

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
            capturedImageUri = uri
        }

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        if (uri != null) {
            capturedImageUri = uri
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    var jenis by remember {
        mutableStateOf<String>("")
    }

    var asal by remember {
        mutableStateOf<String>("")
    }

    var deskripsi by remember {
        mutableStateOf<String>("")
    }

    var isReady by remember {
        mutableStateOf<Boolean>(false)
    }

    var isDetected by remember {
        mutableStateOf<Boolean>(false)
    }
    
    fun predict(){
        val getFile = uriToFile(capturedImageUri, context)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        var tensorImage = TensorImage(DataType.FLOAT32)
        tensorImage.load(BitmapFactory.decodeFile(getFile.path))

        tensorImage = imageProcessor.process(tensorImage)

        val model = BatikDatasets.newInstance(context)

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 224, 224, 3), DataType.FLOAT32)
        inputFeature0.loadBuffer(tensorImage.buffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer.floatArray

        var maxIdx = 0
        outputFeature0.forEachIndexed{index, fl ->
            if(outputFeature0[maxIdx] < fl){
                maxIdx = index
            }
        }

        if(maxIdx == 0){
            jenis = "Batik Ikat Celup"
            asal = "Jawa"
            deskripsi = "Ikat celup atau Jumputan (tie-dye) adalah teknik mewarnai kain dengan cara mengikat kain dengan cara tertentu sebelum dilakukan pencelupan. Di beberapa daerah di Indonesia, teknik ini dikenal dengan berbagai nama lain seperti jumputan pelangi atau cinde (Palembang), tritik atau jumputan (Jawa), serta sasirangan (Banjarmasin). Teknik ikat celup sering dipadukan dengan teknik lain seperti batik."
        }else if(maxIdx == 1){
            jenis = "Batik Insang"
            asal = "Pontianak"
            deskripsi = "Tenun Corak Insang adalah tenunan tradisional khas masyarakat suku Melayu di Kota Pontianak. Tenunan ini dikenal sejak masa Kesultanan Kadriah di bawah kekuasaan Sultan Syarif Abdurrahman Al Qadrie tahun 1771 hingga saat ini. Awalnya Corak Insang hanya digunakan oleh kaum bangsawan di Istana Kadriah. Fungsi tenun Corak Insang adalah sebagai penunjuk identitas status sosial bagi satu keluarga atau satu kelompok dalam kehidupan bermasyarakat dan saat diadakannya pertemuan antar kerajaan."
        }else if(maxIdx == 2){
            jenis = "Batik Kawung"
            asal = "Yogyakata"
            deskripsi = "Batik Kawung adalah motif batik yang bentuknya berupa bulatan mirip buah kawung yang ditata rapi secara geometris. Kadang, motif ini juga ditafsirkan sebagai gambar bunga lotus dengan empat lembar mahkota bunga yang merekah. Lotus adalah bunga yang melambangkan umur panjang dan kesucian."
        }else if(maxIdx == 3){
            jenis = "Batik Megamendung"
            asal = "Cirebon"
            deskripsi = "Batik Megamendung merupakan karya seni batik yang identik dan bahkan menjadi ikon batik daerah Cirebon dan daerah Indonesia lainnya. Motif batik ini mempunyai kekhasan yang tidak ditemui di daerah penghasil batik lain."
        }else if(maxIdx == 4){
            jenis = "Batik Parang"
            asal = "Yogyakata"
            deskripsi = "Batik Parang adalah salah satu motif batik yang paling tua di Indonesia. Parang berasal dari kata Pereng yang berarti lereng. Perengan menggambarkan sebuah garis menurun dari tinggi ke rendah secara diagonal. Susunan motif S jalin-menjalin tidak terputus melambangkan kesinambungan. Bentuk dasar huruf S diambil dari ombak samudra yang menggambarkan semangat yang tidak pernah padam. Batik ini merupakan batik asli Indonesia yang sudah ada sejak zaman keraton Mataram Kartasura (Solo)."
        }

        model.close()
    }

    if (isDetected){
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val getFile = uriToFile(capturedImageUri, context)
            Image(
                bitmap = BitmapFactory.decodeFile(getFile.path).asImageBitmap(),
                contentDescription = null,
                modifier = modifier
                    .size(300.dp)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = jenis,
                maxLines = 1,
                style = MaterialTheme.typography.h5,
                color = Color.Black,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
            Text(
                text = asal,
                maxLines = 1,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.primary,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = deskripsi,
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Justify,
                color = Color.Black,
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            )
            MyTextButton(text = "Try Other Batik", color = MaterialTheme.colors.secondary, textColor = Color.White, modifier = modifier.padding(16.dp), onClick = { isDetected = false })
            Button(
                onClick = { Toast.makeText(context, "This feature is still unavailable", Toast.LENGTH_SHORT).show() },
                shape = Shapes.small,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                ),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .heightIn(min = 48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(text = "Try AR Feature",style = MaterialTheme.typography.button, color = Color.White, modifier = Modifier.padding(start = 8.dp))
            }
        }
    }else {
        Box(modifier = modifier.fillMaxSize()){
            if (capturedImageUri.path?.isNotEmpty() == true) {
                val getFile = uriToFile(capturedImageUri, context)
                if(BitmapFactory.decodeFile(getFile.path) == null){
                    isReady = false
                    Image(
                        painter = painterResource(id = R.drawable.baseline_image_24),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }else {
                    isReady = true
                    Image(
                        bitmap = BitmapFactory.decodeFile(getFile.path).asImageBitmap(),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                    )
                }
            } else{
                Image(
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = null,
                    modifier = modifier
                        .fillMaxSize()
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(32.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White),
                onClick = {
                    if(isReady){
                        predict()

                        if(jenis != ""){
                            isDetected = true
                        }else{
                            Toast.makeText(context, "Batik detection failed", Toast.LENGTH_SHORT).show()
                        }
                    } else{
                        Toast.makeText(context, "Image not ready", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
            IconButton(
                modifier = Modifier
                    .size(130.dp)
                    .align(Alignment.BottomCenter)
                    .padding(32.dp)
                    .clip(RoundedCornerShape(60.dp))
                    .background(Color.White),
                onClick = { val permissionCheckResult =
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                        cameraLauncher.launch(uri)
                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    } }
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
            IconButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(32.dp)
                    .clip(RoundedCornerShape(25.dp))
                    .background(Color.White),
                onClick = { launcher.launch("image/*") }
            ) {
                Icon(
                    imageVector = Icons.Default.Folder,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                )
            }
        }
    }
}

private fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun CameraScreenPreview(){
    VistiqueTheme {
        CameraScreen()
    }
}