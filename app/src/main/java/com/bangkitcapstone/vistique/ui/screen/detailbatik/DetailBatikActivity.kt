package com.bangkitcapstone.vistique.ui.screen.detailbatik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.bangkitcapstone.vistique.model.dummyBatik
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

class DetailBatikActivity : ComponentActivity() {
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
                    DetailBatikScreen(id = extra_id.toString())
                }
            }
        }
    }
}