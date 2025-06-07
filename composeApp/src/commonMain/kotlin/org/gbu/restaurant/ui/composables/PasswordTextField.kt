package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.hide_icon
import restaurantdemo.composeapp.generated.resources.hide_password
import restaurantdemo.composeapp.generated.resources.show_icon
import restaurantdemo.composeapp.generated.resources.show_password

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier.fillMaxWidth(),
    value: String,
    readOnly: Boolean = false,
    isError: Boolean = false,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = enabled) {
        if (!enabled) isPasswordVisible.value = false
    }

    TextField(
        isError = isError,
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        readOnly = readOnly,
        value = value,
        onValueChange = { onValueChange(it) },
        trailingIcon = {
            IconButton(onClick = {
                if (enabled) {
                    isPasswordVisible.value = !isPasswordVisible.value
                }
            }) {
                when (isPasswordVisible.value) {
                    true -> Icon(
                        painter = painterResource(Res.drawable.hide_icon),
                        contentDescription = stringResource(Res.string.hide_password),
                        tint = MaterialTheme.colorScheme.primary
                    )

                    false -> Icon(
                        painter = painterResource(Res.drawable.show_icon),
                        contentDescription = stringResource(Res.string.show_password),
                        tint = Color.Gray
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = when (isPasswordVisible.value) {
            true -> VisualTransformation.None
            false -> PasswordVisualTransformation()
        }
    )
}