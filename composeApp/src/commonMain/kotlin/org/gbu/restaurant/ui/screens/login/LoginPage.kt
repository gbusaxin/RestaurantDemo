package org.gbu.restaurant.ui.screens.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.ui.composables.DefaultButton
import org.gbu.restaurant.ui.composables.DefaultScreenUI
import org.gbu.restaurant.ui.composables.PasswordTextField
import org.gbu.restaurant.ui.composables.SimpleImageButton
import org.gbu.restaurant.ui.screens.login.viewmodel.LoginEvent
import org.gbu.restaurant.ui.screens.login.viewmodel.LoginState
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.apple_logo
import restaurantdemo.composeapp.generated.resources.dont_have_account
import restaurantdemo.composeapp.generated.resources.email
import restaurantdemo.composeapp.generated.resources.enter_valid_email
import restaurantdemo.composeapp.generated.resources.enter_valid_password
import restaurantdemo.composeapp.generated.resources.forget_password
import restaurantdemo.composeapp.generated.resources.google_logo
import restaurantdemo.composeapp.generated.resources.or_sign_in_with
import restaurantdemo.composeapp.generated.resources.password
import restaurantdemo.composeapp.generated.resources.sign_in
import restaurantdemo.composeapp.generated.resources.sign_up
import restaurantdemo.composeapp.generated.resources.welcome

@Composable
fun LoginPage(
    state: LoginState,
    events: (LoginEvent) -> Unit,
    errors: Flow<UIComponent>,
    navigateToRegister: () -> Unit,
) {
    var isUsernameError by rememberSaveable { mutableStateOf(false) }
    var isPasswordError by rememberSaveable { mutableStateOf(false) }

    DefaultScreenUI(
        errors = errors,
        progressBarState = state.progressBarState
    ) {
        Box(Modifier.fillMaxSize().padding(16.dp)) {
            Column(
                modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(Res.string.sign_in),
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(Res.string.welcome),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(Modifier.height(32.dp))

                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(stringResource(Res.string.email))
                    Spacer(Modifier.height(4.dp))
                    TextField(
                        isError = isUsernameError,
                        value = state.usernameLogin,
                        onValueChange = {
                            if (it.length < 32) {
                                events(LoginEvent.OnUpdateUsernameLogin(it))
                                isUsernameError = it.isEmpty()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = MaterialTheme.shapes.small,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Email
                        )
                    )
                    AnimatedVisibility(visible = isUsernameError) {
                        Text(
                            text = stringResource(Res.string.enter_valid_email),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    Spacer(Modifier.height(8.dp))

                    Text(stringResource(Res.string.password))
                    Spacer(Modifier.height(4.dp))
                    PasswordTextField(
                        isError = isPasswordError, // TODO(make sure it works because it has been commented)
                        value = state.passwordLogin,
                        onValueChange = {
                            events(LoginEvent.OnUpdatePasswordLogin(it))
                            isPasswordError =
                                it.length < 8 // TODO(create a validation for password)
                        }
                    )

                    AnimatedVisibility(visible = isPasswordError) {
                        Text(
                            text = stringResource(Res.string.enter_valid_password),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(Res.string.forget_password),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                }

                Spacer(Modifier.height(32.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    DefaultButton(
                        progressBarState = state.progressBarState,
                        text = stringResource(Res.string.sign_in),
                        modifier = Modifier.fillMaxWidth().height(250.dp),
                        onClick = { events(LoginEvent.Login) }
                    )
                    Spacer(Modifier.height(32.dp))
                }

                Column(Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        HorizontalDivider(Modifier.width(75.dp))
                        Text(stringResource(Res.string.or_sign_in_with))
                        HorizontalDivider(Modifier.width(75.dp))
                    }
                    Spacer(Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SimpleImageButton(Res.drawable.apple_logo)
                        SimpleImageButton(Res.drawable.google_logo)// TODO(paste onclick with OAuth2)
                    }
                }
                Spacer(Modifier.height(32.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(stringResource(Res.string.dont_have_account))
                    Spacer(Modifier.height(4.dp))
                    Text(
                        modifier = Modifier.clickable {
                            navigateToRegister()
                        },
                        text = stringResource(Res.string.sign_up),
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
    }

}






