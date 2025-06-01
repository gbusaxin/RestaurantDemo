package org.gbu.restaurant.decompose.root.splash

import org.gbu.restaurant.ui.screens.splash.viewmodel.SplashViewModel

interface SplashComponent {

    val viewModel: SplashViewModel

    fun onSplashTimeFinish(isOnBoardedBefore: Boolean)

}