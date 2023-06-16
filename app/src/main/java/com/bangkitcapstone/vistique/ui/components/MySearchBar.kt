package com.bangkitcapstone.vistique.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkitcapstone.vistique.ui.theme.Shapes
import com.bangkitcapstone.vistique.ui.theme.Typography
import com.bangkitcapstone.vistique.ui.theme.VistiqueTheme

@Composable
fun MySearchBar(
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                modifier = modifier
                    .size(20.dp),
                contentDescription = null
            )
        },

        placeholder = { Text(text = "Search Batik...", style = Typography.subtitle1) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            unfocusedBorderColor = MaterialTheme.colors.secondaryVariant,
            focusedBorderColor = Color.DarkGray,
            placeholderColor = MaterialTheme.colors.secondary,
            textColor = Color.DarkGray
        ),
        shape = Shapes.large,
        modifier = modifier
            .height(45.dp)
            .width(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun MySearchBarPreview(){
    VistiqueTheme {
        MySearchBar()
    }
}