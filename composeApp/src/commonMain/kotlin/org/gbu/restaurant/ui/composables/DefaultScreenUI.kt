package org.gbu.restaurant.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import org.gbu.restaurant.business.core.NetworkState
import org.gbu.restaurant.business.core.Queue
import org.gbu.restaurant.business.core.UIComponent
import org.gbu.restaurant.business.data.network.common.ProgressBarState
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import restaurantdemo.composeapp.generated.resources.Res
import restaurantdemo.composeapp.generated.resources.no_wifi_error
import restaurantdemo.composeapp.generated.resources.no_wifi_icon
import restaurantdemo.composeapp.generated.resources.try_again

@Composable
fun DefaultScreenUI(
    errors: Flow<UIComponent> = MutableSharedFlow(),
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    networkState: NetworkState = NetworkState.Good,
    onTryAgain: () -> Unit = {},
    titleToolbar: String? = null,
    startIconToolbar: ImageVector? = null,
    endIconToolbar: ImageVector? = null,
    onClickStartIconToolbar: () -> Unit = {},
    onClickEndIconToolbar: () -> Unit = {},
    content: @Composable () -> Unit
) {

    val errorQueue = remember {
        mutableStateOf<Queue<UIComponent>>(Queue(mutableListOf()))
    }

    Scaffold(
        topBar = {
            if (titleToolbar != null) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 13.dp, top = 45.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (startIconToolbar != null) {
                        CircleButton(
                            imageVector = startIconToolbar,
                            onClick = { onClickStartIconToolbar() }
                        )
                    } else {
                        Spacer(Modifier.height(16.dp))
                    }
                    Text(titleToolbar, style = MaterialTheme.typography.titleLarge)

                    if (endIconToolbar != null) {
                        CircleButton(
                            imageVector = endIconToolbar,
                            onClick = { onClickEndIconToolbar() }
                        )
                    } else {
                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(top = if (titleToolbar != null) it.calculateTopPadding() else 0.dp)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            content()

            LaunchedEffect(errors) {
                errors.collect { e ->
                    errorQueue.appendToMessageQueue(e)
                }
            }

            if (errorQueue.value.isEmpty().not()) {
                errorQueue.value.peek()?.let { component ->
                    if (component is UIComponent.Dialog) {
                        CreateUIComponentDialog(
                            title = component.alert.title,
                            description = component.alert.message,
                            onRemovedHeadFromQueue = { errorQueue.removeHeadMessage() }
                        )
                    }
                    if (component is UIComponent.ToastSimple) {
                        ShowSnackBar(
                            title = component.title,
                            snackBarVisibleState = true,
                            onDismiss = { errorQueue.removeHeadMessage() },
                            modifier = Modifier.align(Alignment.BottomCenter)
                        )
                    }
                }
            }

            if (networkState == NetworkState.Failed && progressBarState == ProgressBarState.Idle) {
                FailedNetworkScreen(onTryAgain = onTryAgain)
            }

            if (progressBarState is ProgressBarState.LoadingWithLogo) {
                LoadingWithLogoScreen()
            }

            if (progressBarState is ProgressBarState.ScreenLoading || progressBarState is ProgressBarState.FullScreenLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun LoadingWithLogoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator() // TODO implement it with app logo, not necessary
        }
    }
}

@Composable
fun FailedNetworkScreen(onTryAgain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painterResource(Res.drawable.no_wifi_icon), "no wifi icon")
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(Res.string.no_wifi_error),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        DefaultButton(
            text = stringResource(Res.string.try_again)
        ) {
            onTryAgain()
        }
    }
}

private fun MutableState<Queue<UIComponent>>.appendToMessageQueue(
    uiComponent: UIComponent
) {
    if (uiComponent is UIComponent.None) return

    val queue = this.value
    queue.add(uiComponent)

    this.value = Queue(mutableListOf())
    this.value = queue
}

private fun MutableState<Queue<UIComponent>>.removeHeadMessage() {
    if (this.value.isEmpty()) return

    val queue = this.value
    queue.remove()
    this.value = Queue(mutableListOf())
    this.value = queue
}