package com.intechbel.techtask.design.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage


@Composable
fun ThImage(
    modifier: Modifier = Modifier,
    image: String,
    contentDescription: String? = null,
) {
    AsyncImage(
        model = image,
        contentDescription = contentDescription,
        modifier = modifier,
    )
}