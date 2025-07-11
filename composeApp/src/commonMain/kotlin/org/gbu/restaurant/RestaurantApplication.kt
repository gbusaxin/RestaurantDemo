package org.gbu.restaurant

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.moriatsushi.insetsx.SystemBarsBehavior
import com.moriatsushi.insetsx.rememberWindowInsetsController
import org.gbu.restaurant.business.common.Context
import org.gbu.restaurant.decompose.root.RestaurantRoot
import org.gbu.restaurant.decompose.root.RestaurantRootImpl
import org.gbu.restaurant.ui.screens.BottomNavPage
import org.gbu.restaurant.ui.screens.ContactPage
import org.gbu.restaurant.ui.screens.LoginPage
import org.gbu.restaurant.ui.screens.PhoneVerificationPage
import org.gbu.restaurant.ui.screens.on_boarding.OnBoardingPage
import org.gbu.restaurant.ui.screens.signin_options.SignInOptionsPage
import org.gbu.restaurant.ui.screens.splash.SplashPage
import org.gbu.restaurant.viewmodels.LocalUser

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RestaurantApplication(
    context: Context,
    root: RestaurantRootImpl
) {
    val windowsInsets = rememberWindowInsetsController()
    val user by root.rootViewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        windowsInsets?.let {
            windowsInsets.setIsNavigationBarsVisible(false)
            windowsInsets.setIsStatusBarsVisible(true)
            windowsInsets.setSystemBarsBehavior(SystemBarsBehavior.Immersive)
        }
    }

    LaunchedEffect(root.rootViewModel.tokenManager.state.value.isTokenAvailable) {
        if (!root.rootViewModel.tokenManager.state.value.isTokenAvailable) {
            root.handleInvalidToken()
        }
    }

    RestaurantTheme {
        CompositionLocalProvider(
            LocalUser provides user
        ) {
            SharedTransitionLayout {
                val stack by root.backstack.subscribeAsState()

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Children(
                        stack = stack,
                        modifier = Modifier.weight(1f)
                    ) { childCreated ->
                        when (val child = childCreated.instance) {
                            is RestaurantRoot.MainDestinationChild.Splash -> {
                                SplashPage(
                                    viewModel = child.component.viewModel,
                                    onSplashFinished = { onBoardedBefore ->
                                        child.component.onSplashTimeFinish(
                                            isOnBoardedBefore = onBoardedBefore
                                        )
                                    }
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.OnBoarding -> {
                                OnBoardingPage(
                                    onGetStarted = { child.component.onBoarded() },
                                    state = child.component.viewModel.state.value,
                                    action = child.component.viewModel.action,
                                    events = child.component.viewModel::onTriggerEvent,
                                    errors = child.component.viewModel.errors
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.SignInOptions -> {
                                SignInOptionsPage(
                                    state = child.component.singInOptionsViewModel.state.value,
                                    errors = child.component.singInOptionsViewModel.errors,
                                    action = child.component.singInOptionsViewModel.action,
                                    events = child.component.singInOptionsViewModel::onTriggerEvent,
                                    onCreateAccount = { child.component.onCreateAccountClicked() },
                                    onSignInAccount = { child.component.onSignInToAccountClicked() },
                                    onSignInGoogleAccount = {}, // TODO oauth2
                                    onSignInAppleAccount = {} // TODO oauth2
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.Login -> {
                                LoginPage(
                                    viewModel = child.component.loginViewModel,
                                    onUserAuthenticated = { user, rememberMe ->
                                        child.component.onAuthenticationSuccess(
                                            user = user,
                                            rememberMe = rememberMe
                                        )
                                    }
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.ContactInfo -> {
                                ContactPage(
                                    viewModel = child.component.contactsViewModel
                                ) {
                                    child.component.onOtpSent()
                                }
                            }

                            is RestaurantRoot.MainDestinationChild.PhoneVerification -> {
                                PhoneVerificationPage(
                                    viewModel = child.component.verifyPhoneViewModel
                                ) {
                                    child.component.onVerificationCompleted()
                                }
                            }

                            is RestaurantRoot.MainDestinationChild.BottomNavHolder -> {
                                BottomNavPage(
                                    bottomNavComponent = child.component,
                                    context = context,
                                    logout = {
                                        child.component.onNavigationToMainChild(
                                            RestaurantRootImpl.MainNavigationConfig.Splash
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}