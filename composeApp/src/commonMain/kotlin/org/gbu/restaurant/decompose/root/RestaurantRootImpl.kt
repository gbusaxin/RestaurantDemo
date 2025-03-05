package org.gbu.restaurant.decompose.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gbu.restaurant.decompose.onboarding.OnBoardingComponentImpl
import org.gbu.restaurant.decompose.splash.SplashComponentImpl
import org.gbu.restaurant.viewmodels.RootViewModel
import org.koin.core.KoinApplication

class RestaurantRootImpl(
    val componentContext: ComponentContext,
    override val koinApplication: KoinApplication
) : RestaurantRoot, ComponentContext by componentContext {

    private val navigation = StackNavigation<MainNavigationConfig>()
    private val mainDispatcher = CoroutineScope(Dispatchers.Main)
    override val rootViewModel: RootViewModel
        get() = instanceKeeper.getOrCreate { RootViewModel() }
    private val _backstack = this.childStack(
        source = navigation,
        initialConfiguration = MainNavigationConfig.Splash, // Splash is first screen by default
        handleBackButton = true
    ) { config, context ->
        createChildFactory(config, context)
    }
    override val backstack = _backstack

    private fun createChildFactory(
        config: MainNavigationConfig,
        componentContext: ComponentContext,
    ): RestaurantRoot.MainDestinationChild {
        return when (config) {
            is MainNavigationConfig.Splash -> RestaurantRoot.MainDestinationChild.Splash(
                component = buildSplashComponent(
                    context = componentContext
                )
            )

            is MainNavigationConfig.OnBoarding -> RestaurantRoot.MainDestinationChild.OnBoarding(
                component = createOnBoardingComponent(
                    context = componentContext
                )
            )

            is MainNavigationConfig.SignInOptions -> {
                TODO()
            }

            else -> TODO()
        }
    }

    private fun buildSplashComponent(
        context: ComponentContext
    ) = SplashComponentImpl(
        componentContext = context,
        onSplashTimeFinished = { isOnboardedBefore ->
            mainDispatcher.launch {
                if (isOnboardedBefore) {
                    navigation.replaceCurrent(configuration = MainNavigationConfig.SignInOptions)
                } else {
                    navigation.replaceCurrent(configuration = MainNavigationConfig.OnBoarding)
                }
            }
        }
    )

    private fun createOnBoardingComponent(
        context: ComponentContext
    ) = OnBoardingComponentImpl(componentContext = context, onOnBoardingFinished = {
        mainDispatcher.launch {
            navigation.replaceCurrent(MainNavigationConfig.SignInOptions)
        }
    })

    sealed class MainNavigationConfig : Parcelable {
        @Parcelize
        data object Splash : MainNavigationConfig()

        @Parcelize
        data object OnBoarding : MainNavigationConfig()

        @Parcelize
        data object SignInOptions : MainNavigationConfig()
    }

}