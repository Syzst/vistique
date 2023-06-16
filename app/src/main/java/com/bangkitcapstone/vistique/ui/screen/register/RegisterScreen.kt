package com.bangkitcapstone.vistique.ui.screen.register

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.MainActivity
import com.bangkitcapstone.vistique.R
import com.bangkitcapstone.vistique.ui.components.MyPassword
import com.bangkitcapstone.vistique.ui.components.MyTextBox
import com.bangkitcapstone.vistique.ui.components.MyTextButton
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RegisterScreen() {
    val context = LocalContext.current as Activity

    lateinit var auth: FirebaseAuth

    val eml = remember { mutableStateOf<String>("") }
    val pwd = remember { mutableStateOf<String>("") }

    auth = Firebase.auth

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            context.startActivity(Intent(context, MainActivity::class.java))
            context.finishAffinity()
        }
    }

    fun register(){
        auth.createUserWithEmailAndPassword(eml.value, pwd.value)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        context,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
            }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(64.dp))
            MyTextBox(teks = "Email", query = eml)
            Spacer(modifier = Modifier.height(16.dp))
            MyPassword(teks = "Password", query = pwd)
            Spacer(modifier = Modifier.height(32.dp))
            MyTextButton(text = "Sign Up", color = Color.Blue, textColor = Color.White, onClick = {
                if(eml.value != "" && pwd.value != ""){
                    register()
                }else{
                    Toast.makeText(context, "Lengkapi form terlebih dahulu", Toast.LENGTH_SHORT).show()
                }
            })
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

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun RegisterScreenPreview() {
    VistiqueTheme {
        RegisterScreen()
    }
}