package com.example.myclothingstore.ui.login

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle

@Composable
fun ShowAuthNavigationText(
    text: String = "",
    clickableText: String = "",
    onClick: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color(0xFF676767))) {
            append(text)
        }
        pushStringAnnotation(tag = "CLICKABLE", annotation = "clickable")
        withStyle(
            style = SpanStyle(
                color = Color(0xFFF83758),
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(clickableText)
        }
        pop()
    }

    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "CLICKABLE", start = offset, end = offset)
                .firstOrNull()?.let {
                    onClick()
                }

        },
        style = MaterialTheme.typography.bodyLarge
    )
}
