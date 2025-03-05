package org.gbu.restaurant.decompose.splash

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.SplashViewModel

class SplashComponentImpl(
    componentContext: ComponentContext,
    val onSplashTimeFinished: (onboardedBefore: Boolean) -> Unit
) : SplashComponent, ComponentContext by componentContext {

    override val viewModel: SplashViewModel
        get() = instanceKeeper.getOrCreate { SplashViewModel() }

    override fun onSplashTimeFinish(isOnBoardedBefore: Boolean) {
        onSplashTimeFinished(isOnBoardedBefore)
    }

}