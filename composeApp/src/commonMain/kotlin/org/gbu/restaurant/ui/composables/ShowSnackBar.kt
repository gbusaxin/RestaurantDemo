package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShowSnackBar(
    modifier: Modifier = Modifier,
    snackBarVisibleState: Boolean,
    title: String,
    onDismiss: () -> Unit
) {
    if (snackBarVisibleState) {
        Snackbar(
            modifier = modifier.padding(16.dp),
            action = {
                Button(onClick = {
                    onDismiss()
                }) {
                    Text("Ok") // TODO replace with string resources
                }
            }
        ) {
            Text(text = title, style = MaterialTheme.typography.bodyMedium)
        }
    }
}