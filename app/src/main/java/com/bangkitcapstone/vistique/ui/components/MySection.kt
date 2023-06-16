package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Text(text = title, style = MaterialTheme.typography.h3, modifier = modifier
                .padding(bottom = 16.dp)
                .padding(horizontal = 16.dp))
        }
        content()
    }
}