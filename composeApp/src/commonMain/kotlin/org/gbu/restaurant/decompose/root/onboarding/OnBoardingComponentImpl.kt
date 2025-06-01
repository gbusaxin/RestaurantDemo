package org.gbu.restaurant.decompose.root.onboarding

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.on_boarding.viewmodel.OnBoardingViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class OnBoardingComponentImpl(
    componentContext: ComponentContext,
    val onOnBoardingFinished: () -> Unit
) : OnBoardingComponent, ComponentContext by componentContext, KoinComponent {

    override val viewModel: OnBoardingViewModel by inject<OnBoardingViewModel>()

    override fun onBoarded() {
        onOnBoardingFinished()
    }
}