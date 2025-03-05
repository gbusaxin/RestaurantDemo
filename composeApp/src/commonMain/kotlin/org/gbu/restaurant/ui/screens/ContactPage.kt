package org.gbu.restaurant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.ui.composables.CustomButton
import org.gbu.restaurant.ui.composables.InteractionBlocker
import org.gbu.restaurant.ui.composables.LogoBox
import org.gbu.restaurant.ui.composables.ProcessTimeLine
import org.gbu.restaurant.viewmodels.ContactsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.app_name
import restaurantdemo.composeapp.generated.resources.by_continue_you_agree_terms
import restaurantdemo.composeapp.generated.resources.facing_any_issues
import restaurantdemo.composeapp.generated.resources.hot_food_icon
import restaurantdemo.composeapp.generated.resources.phone_number
import restaurantdemo.composeapp.generated.resources.privacy_and_policies
import restaurantdemo.composeapp.generated.resources.terms_and_conditions
import restaurantdemo.composeapp.generated.resources.verify_phone_number
import restaurantdemo.composeapp.generated.resources.you_will_receive_otp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactPage(
    viewModel: ContactsViewModel,
    onOtpSent: () -> Unit
) {
    val phone by remember { viewModel.phone }
    val phoneInputError by remember { viewModel.phoneInputError }
    val isLoading by remember { viewModel.isLoading }

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = isLoading
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxWidth()
                    .shadow(
                        elevation = 8.dp,
                        ambientColor = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 12,
                            bottomEndPercent = 12
                        )
                    )
                    .padding(bottom = 6.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStartPercent = 0,
                            topEndPercent = 0,
                            bottomStartPercent = 12,
                            bottomEndPercent = 12
                        )
                    )
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    LogoBox(
                        icon = painterResource(Res.drawable.hot_food_icon),
                        backgroundColor = MaterialTheme.colorScheme.surface
                    )
                    Text(
                        text = stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.displayMedium
                            .copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current
                Spacer(modifier = Modifier.weight(1f))
                ProcessTimeLine(
                    stepsCount = 5,
                    currentStep = 1
                )
                Spacer(Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        isError = phoneInputError.isNotBlank(),
                        value = phone,
                        textStyle = MaterialTheme.typography.bodySmall
                            .copy(fontWeight = FontWeight.Bold),
                        onValueChange = { viewModel.updatePhoneNumber(phoneNum = it) },
                        placeholder = {
                            Text(
                                text = "+421 ".plus(stringResource(Res.string.phone_number)), // TODO replace hardcode text
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
                            if (phoneInputError.isNotBlank()) {
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = phoneInputError,
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(
                                            fontWeight = FontWeight.Normal,
                                            color = Color.Red
                                        )
                                )
                            } else {
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = stringResource(Res.string.you_will_receive_otp),
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(fontWeight = FontWeight.Normal),
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    )
                }
                CustomButton(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    text = stringResource(Res.string.verify_phone_number),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal),
                    containerColor = MaterialTheme.colorScheme.primary,
                    enabled = !isLoading,
                    onClick = {
                        keyboardController?.hide()
                        viewModel.sendOtp(phone = phone, onOtpSent = onOtpSent)
                    },
                    leadingIcon = if (isLoading) {
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
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {  } // TODO implement
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    text = stringResource(Res.string.facing_any_issues),
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                                .clickable {  }, // TODO implement
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
                                .clickable {  }, // TODO implement
                            text = stringResource(Res.string.privacy_and_policies),
                            style = MaterialTheme.typography.titleMedium
                                .copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}