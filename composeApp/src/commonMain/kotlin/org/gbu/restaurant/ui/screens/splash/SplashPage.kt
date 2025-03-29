package org.gbu.restaurant.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.gbu.restaurant.ui.composables.MultiStateAnimationCircleFilledCanvas
import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashState
import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashViewModel
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.app_name

@Composable
fun SplashPage(
    viewModel: SplashViewModel,
    onSplashFinished: (onboardedBefore: Boolean) -> Unit
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        SplashState.Waiting -> {
           SplashPageContent()
        }

        is SplashState.Success -> {
            (state as SplashState.Success).let {
                onSplashFinished(it.onBoardBefore)
            }
        }
    }
}

@Composable
private fun SplashPageContent() {

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter),
            contentAlignment = Alignment.BottomCenter
        ) {
            MultiStateAnimationCircleFilledCanvas()
        }

        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(Res.string.app_name),
                style = MaterialTheme.typography.displayLarge,
                color = Color.DarkGray,
                fontWeight = FontWeight.Bold
            )
        }
    }
}