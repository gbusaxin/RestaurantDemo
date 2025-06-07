package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SimpleImageButton(
    image: DrawableResource,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = Modifier.size(70.dp),
        onClick = onClick,
        border = BorderStroke(1.dp, Color.DarkGray),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
            modifier = Modifier.size(70.dp)
        )
    }
}