package com.bangkitcapstone.vistique.ui.screen.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.bangkitcapstone.vistique.ui.screen.detailbatik.DetailBatikScreen
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

class ArticleActivity : ComponentActivity() {
    companion object {
        const val EXTRA_ID = "ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val extra_id = intent.getStringExtra(EXTRA_ID)
        setContent {
            VistiqueTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArticleScreen(id = extra_id.toString())
                }
            }
        }
    }
}