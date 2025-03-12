package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SignInWithButton(
    modifier: Modifier = Modifier,
    text: String,
    optionIcon: Painter,
    textStyle: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    enabled: Boolean = true,
    containerColor: Color,
    contentColor: Color,
    disabledContainerColor: Color = Color.LightGray,
    disabledContentColor: Color = Color.Gray,
    shape: Shape = MaterialTheme.shapes.medium,
    iconTint: Color = Color.Unspecified,
    padding: PaddingValues = PaddingValues(
        horizontal = 24.dp,
        vertical = 12.dp
    ),
    onClick: () -> Unit
){

    Button(
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor,
            disabledContainerColor = disabledContainerColor
        ),
        onClick = onClick,
        shape = shape,
        contentPadding = padding
    ){
        Icon(
            modifier = Modifier
                .padding(end = 20.dp)
                .size(24.dp),
            painter = optionIcon,
            contentDescription = "Sign in button",
            tint = iconTint
        )
        Text(
            text = text,
            style = textStyle
        )
    }

}