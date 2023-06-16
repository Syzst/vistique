package com.bangkitcapstone.vistique.ui.screen.login

import android.app.Activity
import android.content.DialogInterface.OnClickListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.bangkitcapstone.vistique.ui.screen.register.RegisterActivity
import com.bangkitcapstone.vistique.ui.theme.Coyote
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(

) {
    val context = LocalContext.current as Activity
    val eml = remember { mutableStateOf<String>("") }
    val pwd = remember { mutableStateOf<String>("") }

    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var auth: FirebaseAuth

    val gso = GoogleSignInOptions
        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    googleSignInClient = GoogleSignIn.getClient(context, gso)
    auth = Firebase.auth

    fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null){
            context.startActivity(Intent(context, MainActivity::class.java))
            context.finishAffinity()
        }
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(context, "signInWithCredential:failure", Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    var resultLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(context, "Google sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    fun logIn(){
        auth.signInWithEmailAndPassword(eml.value, pwd.value)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
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
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
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
            MyTextButton(text = "Sign In", color = Coyote, textColor = Color.White, onClick = {
                if(eml.value != "" && pwd.value != ""){
                    logIn()
                }else{
                    Toast.makeText(context, "Lengkapi form terlebih dahulu", Toast.LENGTH_LONG).show()
                }
            })
            Spacer(modifier = Modifier.height(16.dp))
            LinkButton(teks = "Sign in with Google", logo = R.drawable.icons8_google, onClick = {signIn()})
            Spacer(modifier = Modifier.height(16.dp))
            LinkButton(teks = "Sign in with Apple ID", logo = R.drawable.icons8_apple, onClick = {
                Toast.makeText(context, "This feature is still unavailable", Toast.LENGTH_SHORT).show()
            })
            Spacer(modifier = Modifier.height(16.dp))
        }
        RegisterButton(modifier = Modifier.align(Alignment.BottomCenter), onClick = {context.startActivity(
            Intent(context, RegisterActivity::class.java)
        )})
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun LoginScreenPreview() {
    VistiqueTheme {
        LoginScreen()
    }
}

@Composable
fun LinkButton(
    teks: String,
    logo: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
        Image(
            painter = painterResource(id = logo),
            contentDescription = ""
        )
        Text(text = teks, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun RegisterButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
){
    OutlinedButton(
        onClick = onClick,
        shape = Shapes.small,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent,
            contentColor = Color.Blue
        ),
        border = BorderStroke(1.dp, Color.Blue),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    )
    {
        Text(text = "Make new account")
    }
}