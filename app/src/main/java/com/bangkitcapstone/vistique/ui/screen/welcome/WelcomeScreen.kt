package com.bangkitcapstone.vistique.ui.screen.welcome

import android.app.Activity
import android.content.Intent
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.ui.screen.login.LoginActivity
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun WelcomeScreen(){
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = (LocalContext.current as? Activity)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.batik_2), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize(), alpha = 0.2F,)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.TopStart)) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    Modifier
                        .size(400.dp)
                        .padding(top = 128.dp)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(text = "Vistique is a unique and innovative batik brand that blends traditional batik techniques with modern fashion trends.", style = MaterialTheme.typography.h2, modifier = Modifier
                    .padding(top = 48.dp)
                    .padding(horizontal = 64.dp))
                Button(
                    onClick = { context?.startActivity(Intent(context, LoginActivity::class.java))
                        context?.finishAffinity() },
                    modifier = Modifier
                        .padding(start = 64.dp)
                        .padding(top = 32.dp)
                ) {
                    Text(text = "Get Started", style = MaterialTheme.typography.button, color = Color.White,)
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun WelcomeScreenPreview(

){
    VistiqueTheme {
        WelcomeScreen()
    }
}