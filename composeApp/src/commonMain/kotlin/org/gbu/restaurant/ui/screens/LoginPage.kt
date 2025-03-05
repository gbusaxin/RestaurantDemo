package org.gbu.restaurant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.data.entity.User
import org.gbu.restaurant.ui.composables.CustomButton
import org.gbu.restaurant.ui.composables.InteractionBlocker
import org.gbu.restaurant.ui.composables.LogoBox
import org.gbu.restaurant.ui.states.LoginUIState
import org.gbu.restaurant.viewmodels.LoginViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.app_name
import restaurantdemo.composeapp.generated.resources.by_continue_you_agree_terms
import restaurantdemo.composeapp.generated.resources.facing_any_issues
import restaurantdemo.composeapp.generated.resources.hot_food_icon
import restaurantdemo.composeapp.generated.resources.phone_number
import restaurantdemo.composeapp.generated.resources.privacy_and_policies
import restaurantdemo.composeapp.generated.resources.sign_in_with_restaurant_account
import restaurantdemo.composeapp.generated.resources.terms_and_conditions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginPage(
    viewModel: LoginViewModel,
    onUserAuthenticated: (user: User, rememberMe: Boolean) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val phone by remember { viewModel.phone }
    val password by remember { viewModel.password }

    LaunchedEffect(uiState) {
        if (uiState is LoginUIState.Authenticated)
            onUserAuthenticated((uiState as LoginUIState.Authenticated).user, false)
    }

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = uiState is LoginUIState.Loading
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(vertical = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LogoBox(
                        icon = painterResource(Res.drawable.hot_food_icon),
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        iconColor = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.displayMedium
                            .copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(48.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState is LoginUIState.Error,
                    value = phone,
                    textStyle = MaterialTheme.typography.bodySmall
                        .copy(fontWeight = FontWeight.Bold),
                    onValueChange = { viewModel.updatePhone(it) },
                    placeholder = {
                        Text(
                            text = "421 ".plus(stringResource(Res.string.phone_number)),
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onBackground
                                .copy(alpha = 0.6f)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Done
                    ),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    supportingText = {
                        if (uiState is LoginUIState.Error.Phone) {
                            Text(
                                modifier = Modifier.padding(top = 6.dp),
                                text = (uiState as LoginUIState.Error).message,
                                style = MaterialTheme.typography.titleMedium
                                    .copy(
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Red
                                    )
                            )
                        }
                    }
                )
                Spacer(Modifier.height(12.dp))
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    isError = uiState is LoginUIState.Error,
                    value = password,
                    textStyle = MaterialTheme.typography.bodySmall
                        .copy(fontWeight = FontWeight.Bold),
                    onValueChange = { viewModel.updatePassword(it) },
                    placeholder = {
                        Text(
                            text = "Your password", // TODO put password example
                            style = MaterialTheme.typography.bodySmall
                                .copy(fontWeight = FontWeight.Medium),
                            color = MaterialTheme.colorScheme.onBackground
                                .copy(alpha = 0.6f)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                        focusedContainerColor = MaterialTheme.colorScheme.surface,
                        cursorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    ),
                    supportingText = {
                        if (uiState is LoginUIState.Error.Password) {
                            Text(
                                modifier = Modifier.padding(top = 6.dp),
                                text = (uiState as LoginUIState.Error).message,
                                style = MaterialTheme.typography.titleMedium
                                    .copy(
                                        fontWeight = FontWeight.Normal,
                                        color = Color.Red
                                    )
                            )
                        }
                    }
                )
                Spacer(modifier = Modifier.height(24.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.sign_in_with_restaurant_account),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal),
                    containerColor = MaterialTheme.colorScheme.primary,
                    enabled = uiState !is LoginUIState.Loading,
                    onClick = {
                        keyboardController?.hide()
                        viewModel.authenticateUser(phone = phone, password = password)
                    },
                    leadingIcon = if (uiState is LoginUIState.Loading) {
                        {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(end = 20.dp)
                                    .size(24.dp),
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    } else null
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { } // TODO implement
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    text = stringResource(Res.string.facing_any_issues),
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = stringResource(Res.string.by_continue_you_agree_terms),
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Justify
            )
            FlowRow(
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }, // TODO implement it
                    text = stringResource(Res.string.terms_and_conditions),
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = " & ",
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }, // TODO
                    text = stringResource(Res.string.privacy_and_policies),
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}








