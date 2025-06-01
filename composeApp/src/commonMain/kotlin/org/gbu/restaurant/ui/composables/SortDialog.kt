package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.business.constants.SortConstants
import org.gbu.restaurant.business.core.UIComponentState
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchEvent
import org.gbu.restaurant.ui.screens.search.viewmodel.SearchState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.highest_price
import restaurantdemo.composeapp.generated.resources.lowest_price
import restaurantdemo.composeapp.generated.resources.newest
import restaurantdemo.composeapp.generated.resources.oldest
import restaurantdemo.composeapp.generated.resources.sort

@Composable
fun SortDialog(
    state: SearchState,
    events: (SearchEvent) -> Unit
) {
    val selectedSort = mutableStateOf(state.selectedSort)

    CustomAlertDialog(
        onDismissRequest = {
            events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
        },
        modifier = Modifier.fillMaxWidth(0.9f).background(MaterialTheme.colorScheme.background)
    ) {
        Column(Modifier.fillMaxWidth().padding(16.dp)) {
            Text(
                text = stringResource(Res.string.sort),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(16.dp))

            Row(Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(SortConstants.NEWEST))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == SortConstants.NEWEST, onCheckedChange = {})
                Text(
                    text = stringResource(Res.string.newest),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Row(Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(SortConstants.OLDEST))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = selectedSort.value == SortConstants.OLDEST, onCheckedChange = {})
                Text(
                    text = stringResource(Res.string.oldest),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(SortConstants.HIGHER_PRICE))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedSort.value == SortConstants.HIGHER_PRICE,
                    onCheckedChange = {})
                Text(
                    text = stringResource(Res.string.highest_price),
                    style = MaterialTheme.typography.labelLarge
                )
            }

            Row(modifier = Modifier.fillMaxWidth().noRippleClickable {
                events(SearchEvent.OnUpdateSelectedSort(SortConstants.LOWEST_PRICE))
                events(SearchEvent.OnUpdateSortDialogState(UIComponentState.Hide))
            }, verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedSort.value == SortConstants.LOWEST_PRICE,
                    onCheckedChange = {})
                Text(
                    text = stringResource(Res.string.lowest_price),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}