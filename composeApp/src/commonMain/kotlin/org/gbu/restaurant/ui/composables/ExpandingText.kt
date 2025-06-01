package org.gbu.restaurant.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.read_more
import restaurantdemo.composeapp.generated.resources.show_less

private const val MINIMIZED_MAX_LINES = 3

@Composable
fun ExpandingText(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    onExpandedChanged: (Boolean) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    val textLayoutResultState = remember { mutableStateOf<TextLayoutResult?>(null) }
    var isClickable by remember { mutableStateOf(false) }
    val finalText = mutableStateOf(text)

    val endOfTitle =
        if (isExpanded) stringResource(Res.string.show_less) else stringResource(Res.string.read_more)

    val textLayoutResult = textLayoutResultState.value

    LaunchedEffect(textLayoutResult) {
        if (textLayoutResult == null) return@LaunchedEffect

        when {
            isExpanded -> {
                finalText.value = "$text $endOfTitle"
            }

            !isExpanded && textLayoutResult.hasVisualOverflow -> {
                val lastCharIndex = textLayoutResult.getLineEnd(MINIMIZED_MAX_LINES - 1)
                val showMoreText = "... $endOfTitle"
                val adjustedText = text
                    .substring(startIndex = 0, endIndex = lastCharIndex)
                    .dropLast(showMoreText.length)
                    .dropLastWhile { it == ' ' || it == '.' }

                finalText.value = "$adjustedText$showMoreText"

                isClickable = true
            }
        }
    }

    Text(
        text = finalText.value,
        maxLines = if (isExpanded) Int.MAX_VALUE else MINIMIZED_MAX_LINES,
        style = style,
        onTextLayout = { textLayoutResultState.value = it },
        modifier = Modifier.clickable(enabled = isClickable) {
            isExpanded = !isExpanded
            onExpandedChanged(isExpanded)
        }.animateContentSize()
    )
}