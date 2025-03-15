package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CreateUIComponentDialog(
    title: String,
    description: String,
    onRemovedHeadFromQueue: () -> Unit
) {
    GenericDialog(
        modifier = Modifier.fillMaxWidth(0.9f),
        title = title,
        description = description,
        onRemovedHeadFromQueue = onRemovedHeadFromQueue
    )
}

@Composable
fun GenericDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    onRemovedHeadFromQueue: () -> Unit
) {
    CustomAlertDialog(
        onDismissRequest = { onRemovedHeadFromQueue() },
        modifier = modifier
    ) {
        Card(
            colors = CardDefaults.cardColors(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(title)
                Text(description)
            }
        }
    }
}