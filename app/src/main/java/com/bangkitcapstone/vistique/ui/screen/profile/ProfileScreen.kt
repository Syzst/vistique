package com.bangkitcapstone.vistique.ui.screen.profile

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardBackspace
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.ui.components.MyTextButton
import com.bangkitcapstone.vistique.ui.screen.favorite.FavoriteActivity
import com.bangkitcapstone.vistique.ui.screen.login.LoginActivity
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    val context = (LocalContext.current as? Activity)

    lateinit var auth: FirebaseAuth
    auth = Firebase.auth

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(16.dp)
        ) {
            Text(
                text = "My Profile",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
                color = Color.Black,
                modifier = modifier.padding(top = 16.dp),
            )
            Spacer(modifier = Modifier.height(32.dp))
            CustomProfile(nama = auth.currentUser?.displayName.toString(), paint = R.drawable.baseline_account_circle_24)
            Spacer(modifier = Modifier.height(64.dp))
            CustomText(teks = auth.currentUser?.displayName.toString(), logo = Icons.Default.AccountCircle, modifier = modifier.padding(bottom = 8.dp))
            CustomText(teks = auth.currentUser?.email.toString(), logo = Icons.Default.Email, modifier = modifier.padding(bottom = 32.dp))
        }
        MyTextButton(text = "Sign out", color = MaterialTheme.colors.secondary, textColor = Color.White, modifier = modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            onClick = onClick)
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
        IconButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd),
            onClick = {
                Toast.makeText(context, "This feature is still unavailable", Toast.LENGTH_SHORT).show()
            }
//            onClick = {context?.startActivity(
//                Intent(context, FavoriteActivity::class.java)
//            )}
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                tint = Color.Red,
            )
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileScreenPreview(

){
    VistiqueTheme {
        ProfileScreen(onClick = {})
    }
}

@Composable
fun CustomText(
    teks: String,
    logo: ImageVector,
    modifier: Modifier = Modifier
) {
    TextField(
        value = teks,
        onValueChange = {},
        enabled = false,
        readOnly = true,
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = logo,
                contentDescription = null)
        },
        textStyle = MaterialTheme.typography.button,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            leadingIconColor = MaterialTheme.colors.secondaryVariant,
            textColor = MaterialTheme.colors.primary,
            disabledIndicatorColor = MaterialTheme.colors.secondaryVariant,
            focusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
            unfocusedIndicatorColor = MaterialTheme.colors.secondaryVariant,
            disabledLeadingIconColor = MaterialTheme.colors.secondaryVariant,
            disabledTextColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.secondaryVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp)
    )
}

@Composable
fun UserImage(
    paint: Int,
){
    Image(
        painter = painterResource(paint),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, MaterialTheme.colors.primary, CircleShape)
    )
}

@Preview(showBackground = true)
@Composable
fun CustomTextPreview(

){
    VistiqueTheme {
        CustomText(teks = "Email", logo = Icons.Default.Email)
    }
}

@Composable
fun CustomProfile(
    modifier: Modifier = Modifier,
    nama: String,
    paint: Int,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserImage(paint = paint)
        Text(
            text = nama,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary,
            modifier = modifier
            .padding(top = 16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun UserImagePreview(

){
    VistiqueTheme {
        UserImage(paint = R.drawable.user)
    }
}