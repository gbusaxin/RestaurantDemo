package org.gbu.restaurant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.ui.composables.CustomButton
import org.gbu.restaurant.ui.composables.DigitsInput
import org.gbu.restaurant.ui.composables.InteractionBlocker
import org.gbu.restaurant.ui.composables.ProcessTimeLine
import org.gbu.restaurant.ui.states.VerifyPhoneUIState
import org.gbu.restaurant.viewmodels.VerifyPhoneViewModel
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.change_phone_number
import restaurantdemo.composeapp.generated.resources.enter_an_otp
import restaurantdemo.composeapp.generated.resources.phone_verification
import restaurantdemo.composeapp.generated.resources.resend_an_otp
import restaurantdemo.composeapp.generated.resources.verify_code

@Composable
fun PhoneVerificationPage(
    viewModel: VerifyPhoneViewModel,
    onVerified: () -> Unit
) {
    val uiState by remember { viewModel.uiState }
    val otp by remember { viewModel.otp }

    LaunchedEffect(uiState) {
        if (uiState is VerifyPhoneUIState.Verified) {
            onVerified()
        }
    }

    InteractionBlocker(
        modifier = Modifier.fillMaxSize(),
        blockCondition = uiState is VerifyPhoneUIState.Verifying
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.phone_verification),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(48.dp))
                ProcessTimeLine(
                    stepsCount = 5,
                    currentStep = 2
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.enter_an_otp),
                    style = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal)
                )
                Spacer(modifier = Modifier.height(36.dp))
                DigitsInput(
                    value = otp,
                    onDigitsChange = { value, isFilled ->
                        viewModel.updateOtp(value = value)
                        if (isFilled)
                            viewModel.validateOtp(otp)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth()){
                    Text(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {  }, // TODO implement
                        text = stringResource(Res.string.resend_an_otp),
                        style = MaterialTheme.typography.titleLarge
                            .copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                CustomButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(Res.string.verify_code),
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    padding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
                    textStyle = MaterialTheme.typography.bodyMedium
                        .copy(fontWeight = FontWeight.Normal),
                    containerColor = MaterialTheme.colorScheme.primary,
                    enabled = uiState !is VerifyPhoneUIState.Verifying && otp.length == 4,
                    onClick = {}, // TODO implement
                    leadingIcon = if (uiState is VerifyPhoneUIState.Verifying){
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
                        .clickable {  }, // TODO implement returning to phone enter page
                    text = stringResource(Res.string.change_phone_number),
                    style = MaterialTheme.typography.titleLarge
                        .copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }

}