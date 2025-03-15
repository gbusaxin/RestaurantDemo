package org.gbu.restaurant.ui.screens.menudetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.business.data.entity.Ingredient
import org.gbu.restaurant.business.data.entity.MenuItem
import org.jetbrains.compose.resources.painterResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.hot_food_icon

@Composable
fun IngredientItem(
    menuItem: MenuItem,
    ingredient: Ingredient
) {
    Box(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .border(
                    width = 2.dp,
                    color = menuItem.bgColor,
                    shape = RoundedCornerShape(35.dp)
                )
        ) {
            Text(
                text = ingredient.ingredient,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 55.dp, end = 8.dp, top = 16.dp, bottom = 16.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Box(modifier = Modifier.padding(start = 4.dp)) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .shadow(elevation = 10.dp, shape = CircleShape)
                    .background(color = menuItem.bgColor, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(Res.drawable.hot_food_icon),
                    contentDescription = "ingredient icon",
                    modifier = Modifier
                        .padding(12.dp)
                        .rotate(-30f),
                    colorFilter = ColorFilter.tint(if (menuItem.bgColor.luminance() > 0.3f) Color.Companion.Black else Color.White)
                )
            }
        }
    }
}