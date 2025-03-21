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
import org.gbu.restaurant.business.di.LocalKoinApplication
import org.gbu.restaurant.decompose.root.RestaurantRoot
import org.gbu.restaurant.ui.screens.BottomNavPage
import org.gbu.restaurant.ui.screens.ContactPage
import org.gbu.restaurant.ui.screens.LoginPage
import org.gbu.restaurant.ui.screens.OnBoardingPage
import org.gbu.restaurant.ui.screens.PhoneVerificationPage
import org.gbu.restaurant.ui.screens.SignInOptionsPage
import org.gbu.restaurant.ui.screens.SplashPage
import org.gbu.restaurant.ui.screens.address.AddressPage
import org.gbu.restaurant.ui.screens.checkout.CheckoutPage
import org.gbu.restaurant.ui.screens.menudetail.MenuDetailsPage
import org.gbu.restaurant.ui.screens.menudetail.viewmodel.MenuDetailEvent
import org.gbu.restaurant.ui.sensor.SensorManager
import org.gbu.restaurant.viewmodels.LocalUser

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun RestaurantApplication(
    root: RestaurantRoot,
    sensorManager: SensorManager?,
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

    RestaurantTheme {
        CompositionLocalProvider(
            LocalKoinApplication provides root.koinApplication,
            LocalUser provides user
        ) {
            SharedTransitionLayout {
                val sharedTransitionScope = this
                val stack by root.backstack.subscribeAsState()
                val current = stack.active.instance

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
                                OnBoardingPage(onGetStarted = { child.component.onBoarded() })
                            }

                            is RestaurantRoot.MainDestinationChild.SignInOptions -> {
                                SignInOptionsPage(
                                    onCreateAccount = { child.component.onCreateAccountClicked() },
                                    onSignInAccount = { child.component.onSignInToAccountClicked() }
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
                                    onNavigationToMainChild = {
                                        child.component.onNavigationToMainChild(it)
                                    },
                                    sharedTransitionScope = sharedTransitionScope
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.MenuDetail -> {
                                val viewModel = child.component.viewModel
                                val id = child.component.menuItemId
                                LaunchedEffect(id) {
                                    viewModel.onTriggerEvent(MenuDetailEvent.GetMenuItem(id))
                                }
                                MenuDetailsPage(
                                    state = viewModel.state.value,
                                    events = viewModel::onTriggerEvent,
                                    errors = viewModel.errors,
                                    goBack = { child.component.onBack() },
                                    sensorManager = sensorManager,
                                    sharedTransitionScope = sharedTransitionScope,
                                    onAddToCart = { /*child.component.onAddToCart()*/ }
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.Checkout -> {
                                val viewModel = child.component.viewModel
                                CheckoutPage(
                                    errors = viewModel.errors,
                                    action = viewModel.action,
                                    state = viewModel.state.value,
                                    events = viewModel::onTriggerEvent,
                                    navigateToAddress = { child.component.navigateToAddress() },
                                    popUp = { child.component.popUp() }
                                )
                            }

                            is RestaurantRoot.MainDestinationChild.Address -> {
                                val viewModel = child.component.viewModel
                                AddressPage(
                                    state = viewModel.state.value,
                                    errors = viewModel.errors,
                                    events = viewModel::onTriggerEvent,
                                    navigateToAddAddress = { child.component.navigateToAddAddress() },
                                    popUp = { child.component.popUp() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}