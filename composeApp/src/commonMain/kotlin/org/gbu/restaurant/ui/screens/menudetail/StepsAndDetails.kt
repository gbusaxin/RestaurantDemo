package org.gbu.restaurant.ui.screens.menudetail

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.data.entity.MenuItem

internal fun LazyListScope.StepsAndDetails(
    menuItem: MenuItem
) {
//    with(sharedTransitionScope) {
    item {
        Text(
            text = menuItem.name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
//                .then(
//                    Modifier.sharedElement(
//                        rememberSharedContentState(key = "menu-name-${menuItem.id}"),
//                        animatedVisibilityScope
//                    )
//                )
        )
        Text(
            text = menuItem.shortDesc,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
//                .then(
//                    Modifier.sharedElement(
//                        rememberSharedContentState(key = "menu-desc-${menuItem.id}"),
//                        animatedVisibilityScope
//                    )
//                )
        )

        AnimateInEffect(
            menuItem = menuItem,
            intervalStart = 0 / (menuItem.ingredients.size + 2).toFloat(),
            content = {
                Text(
                    text = "INGREDIENTS", // todo replace with string resources
                    style = MaterialTheme.typography.displaySmall
                        .copy(fontWeight = FontWeight.Normal),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
            }
        )
    }

    itemsIndexed(menuItem.ingredients) { index, value ->
        AnimateInEffect(
            intervalStart = (index + 1) / (menuItem.ingredients.size + 1).toFloat(),
            menuItem = menuItem,
            content = {
                IngredientItem(menuItem, value)
            }
        )
    }

    item {
        AnimateInEffect(
            menuItem = menuItem,
            intervalStart = (menuItem.ingredients.size + 1) / (menuItem.ingredients.size + 2).toFloat(),
            content = {
                Text(
                    text = "STEPS", // TODO replace with string resources
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontWeight = FontWeight.Thin
                    ),
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                )
            }
        )
    }

}