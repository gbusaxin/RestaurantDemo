package org.gbu.restaurant.decompose.splash

import org.gbu.restaurant.viewmodels.SplashViewModel

interface SplashComponent {

    val viewModel: SplashViewModel

    fun onSplashTimeFinish(isOnBoardedBefore: Boolean)

}