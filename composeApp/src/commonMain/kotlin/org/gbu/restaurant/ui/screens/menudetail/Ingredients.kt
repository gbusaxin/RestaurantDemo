package org.gbu.restaurant.ui.screens.menudetail

import androidx.compose.animation.AnimatedContentScope
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
import org.gbu.restaurant.business.data.entity.MenuItem

@OptIn(ExperimentalSharedTransitionApi::class)
internal fun LazyListScope.Ingredients(
    menuItem: MenuItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope
) {
    with(sharedTransitionScope) {
        item {
            Text(
                text = menuItem.name,
                style = MaterialTheme.typography.displayMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .then(
                        Modifier.sharedElement(
                            rememberSharedContentState(
                                key = "item-name-${menuItem.id}"
                            ),
                            animatedContentScope
                        )
                    )
            )
            Text(
                text = menuItem.shortDesc,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .then(
                        Modifier.sharedElement(
                            rememberSharedContentState(
                                key = "item-desc-${menuItem.id}"
                            ),
                            animatedContentScope
                        )
                    )
            )

            AnimateInEffect(
                menuItem = menuItem,
                intervalStart = 0 / (menuItem.ingredients.size + 2).toFloat(),
                content = {
                    Text(
                        text = "INGREDIENTS", // todo replace with string resources
                        style = MaterialTheme.typography.bodySmall
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
    }
}