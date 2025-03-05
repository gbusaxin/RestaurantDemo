package org.gbu.restaurant.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.ui.composables.VerticalBrandLogo
import org.gbu.restaurant.ui.states.SplashUIState
import org.gbu.restaurant.viewmodels.SplashViewModel

@Composable
fun SplashPage(
    viewModel: SplashViewModel,
    onSplashFinished: (onboardedBefore: Boolean) -> Unit
){
    val loggedUser by  viewModel.uiState.collectAsState()

    when(loggedUser){
        SplashUIState.Waiting -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerticalBrandLogo()
            }
        }

        is SplashUIState.Success -> {
            (loggedUser as SplashUIState.Success).let {
                onSplashFinished(it.onBoardBefore)
            }
        }
    }

}