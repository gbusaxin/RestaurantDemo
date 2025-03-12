package org.gbu.restaurant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.ui.composables.SignInWithButton
import org.gbu.restaurant.ui.composables.HorizontalBrandLogo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.apple_logo
import restaurantdemo.composeapp.generated.resources.by_continue_you_agree_terms
import restaurantdemo.composeapp.generated.resources.create_an_account
import restaurantdemo.composeapp.generated.resources.google_logo
import restaurantdemo.composeapp.generated.resources.hot_food_icon
import restaurantdemo.composeapp.generated.resources.privacy_and_policies
import restaurantdemo.composeapp.generated.resources.save_in_your_pocket
import restaurantdemo.composeapp.generated.resources.sign_in_with_apple_account
import restaurantdemo.composeapp.generated.resources.sign_in_with_google_account
import restaurantdemo.composeapp.generated.resources.sign_in_with_restaurant_account
import restaurantdemo.composeapp.generated.resources.terms_and_conditions

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SignInOptionsPage(
    onCreateAccount: () -> Unit,
    onSignInAccount: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalBrandLogo()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.6f),
                text = stringResource(Res.string.save_in_your_pocket),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal
                ),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
            SignInWithButton(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                text = stringResource(Res.string.sign_in_with_restaurant_account),
                optionIcon = painterResource(Res.drawable.hot_food_icon),
                textStyle = MaterialTheme.typography.bodyMedium
                    .copy(fontWeight = FontWeight.Normal),
                containerColor = MaterialTheme.colorScheme.onSurface,
                contentColor = MaterialTheme.colorScheme.surface,
                iconTint = MaterialTheme.colorScheme.surface,
                onClick = { onSignInAccount() }
            )
            SignInWithButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.sign_in_with_apple_account),
                optionIcon = painterResource(Res.drawable.apple_logo),
                textStyle = MaterialTheme.typography.bodyMedium
                    .copy(fontWeight = FontWeight.Normal),
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onBackground,
                onClick = { } // TODO(via OAuth 2) put lambda in constructor
            )
            SignInWithButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.sign_in_with_google_account),
                optionIcon = painterResource(Res.drawable.google_logo),
                textStyle = MaterialTheme.typography.bodyMedium
                    .copy(fontWeight = FontWeight.Normal),
                containerColor = MaterialTheme.colorScheme.inversePrimary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                onClick = {} // TODO(via OAuth 2) put lambda in constructor
            )
            Text(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onCreateAccount() }
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                text = stringResource(Res.string.create_an_account),
                style = MaterialTheme.typography.titleLarge
                    .copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.by_continue_you_agree_terms),
                style = MaterialTheme.typography.titleMedium
                    .copy(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Justify
            )
            FlowRow(
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }, // TODO
                    text = stringResource(Res.string.terms_and_conditions),
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = " & ",
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable { }, // TODO
                    text = stringResource(Res.string.privacy_and_policies),
                    style = MaterialTheme.typography.titleMedium
                        .copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }

}