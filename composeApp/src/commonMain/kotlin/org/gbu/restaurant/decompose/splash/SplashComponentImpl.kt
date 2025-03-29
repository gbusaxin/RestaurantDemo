package org.gbu.restaurant.decompose.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashComponentImpl(
    componentContext: ComponentContext,
    val onSplashTimeFinished: (onboardedBefore: Boolean) -> Unit
) : SplashComponent, ComponentContext by componentContext, KoinComponent {

    override val viewModel: SplashViewModel by inject<SplashViewModel>()

    override fun onSplashTimeFinish(isOnBoardedBefore: Boolean) {
        onSplashTimeFinished(isOnBoardedBefore)
    }

}