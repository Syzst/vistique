package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.ui.theme.Coyote
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun MyTextButton(
    text: String,
    color: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = Shapes.small,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = textColor
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
    ) {
        Text(text = text, color = textColor, style = MaterialTheme.typography.button)
    }
}

@Preview(showBackground = true)
@Composable
fun MyButtonPreview(){
    VistiqueTheme {
        MyTextButton(text = "Sign In", color = Coyote, textColor = Color.White, onClick = {})
    }
}