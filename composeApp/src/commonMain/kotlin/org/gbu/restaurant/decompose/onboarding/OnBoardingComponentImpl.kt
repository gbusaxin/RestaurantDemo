package org.gbu.restaurant.decompose.onboarding

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.OnBoardingViewModel

class OnBoardingComponentImpl(
    componentContext: ComponentContext,
    val onOnBoardingFinished: () -> Unit
) : OnBoardingComponent, ComponentContext by componentContext {

    override val viewModel: OnBoardingViewModel
        get() = instanceKeeper.getOrCreate { OnBoardingViewModel() }

    override fun onBoarded() {
        onOnBoardingFinished()
    }
}