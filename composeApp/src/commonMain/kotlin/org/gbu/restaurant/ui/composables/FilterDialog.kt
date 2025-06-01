package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchEvent
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.category
import restaurantdemo.composeapp.generated.resources.filter
import restaurantdemo.composeapp.generated.resources.price
import restaurantdemo.composeapp.generated.resources.reset

@Composable
fun FilterDialog(
    state: SearchState,
    events: (SearchEvent) -> Unit
) {
    var selectedRange by remember { mutableStateOf(state.selectedRange) }
    var selectedCategories = state.selectedCategory.toMutableStateList()

    CustomAlertDialog(
        onDismissRequest = {
            events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
        },
        modifier = Modifier.fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background)
    ) {
        Column(Modifier.fillMaxWidth()) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.filter),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(32.dp))

            Text(
                text = stringResource(Res.string.price),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            RangeSlider(
                value = selectedRange,
                onValueChange = { selectedRange = it },
                valueRange = state.searchFilter.minPrice.toFloat()..state.searchFilter.maxPrice.toFloat(),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$${selectedRange.start.toInt()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$${selectedRange.endInclusive.toInt()}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.category),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(Modifier.height(8.dp))

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(state.searchFilter.categories, { it.id }) {
                    CategoryChipsBox(it, isSelected = selectedCategories.contains(it)) {
                        if (selectedCategories.contains(it)) {
                            selectedCategories.remove(it)
                        } else {
                            selectedCategories.add(it)
                        }
                    }
                }
            }

            Spacer(Modifier.height(32.dp))

            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
                DefaultButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.reset)
                ) {
                    events(SearchEvent.OnUpdateSelectedCategory(listOf()))
                    events(SearchEvent.OnUpdatePriceRange(0f..10f))
                    events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
                    events(SearchEvent.Search())
                }
                Spacer(Modifier.height(16.dp))
                DefaultButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(Res.string.filter)
                ) {
                    events(SearchEvent.OnUpdateSelectedCategory(selectedCategories))
                    events(SearchEvent.OnUpdatePriceRange(selectedRange))
                    events(SearchEvent.OnUpdateFilterDialogState(UIComponentState.Hide))
                    events(
                        SearchEvent.Search(
                            minPrice = selectedRange.start.toInt(),
                            maxPrice = selectedRange.endInclusive.toInt(),
                            categories = selectedCategories
                        )
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
        }
    }
}













