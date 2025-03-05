package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LogoBox(
    icon: Painter,
    logoSize: Dp = 36.dp,
    backgroundColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconColor: Color = MaterialTheme.colorScheme.primary
) {

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(backgroundColor)
            .padding(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(logoSize),
            painter = icon,
            contentDescription = "Logo box icon",
            tint = iconColor
        )
    }

}