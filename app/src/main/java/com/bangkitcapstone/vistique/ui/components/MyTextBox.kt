package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bangkitcapstone.vistique.ui.theme.*

@Composable
fun MyTextBox(
    teks: String,
    modifier: Modifier = Modifier,
    query: MutableState<String>,
) {
    OutlinedTextField(
        value = query.value,
        onValueChange = { text -> query.value = text },
        textStyle = Typography.button,
        placeholder = { Text(text = teks, style = Typography.button) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
            focusedBorderColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.secondary,
            textColor = Color.DarkGray
        ),
        shape = Shapes.medium,
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp)
    )
}

@Composable
fun MyPassword(
    teks: String,
    modifier: Modifier = Modifier,
    query: MutableState<String>,
){
    OutlinedTextField(
        value = query.value,
        onValueChange = { text -> query.value = text },
        textStyle = Typography.button,
        placeholder = { Text(text = teks, style = Typography.button) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant,
            focusedBorderColor = MaterialTheme.colors.primary,
            placeholderColor = MaterialTheme.colors.secondary,
            textColor = Color.DarkGray
        ),
        shape = Shapes.medium,
        visualTransformation = PasswordVisualTransformation(),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 32.dp)
    )
}