package org.gbu.restaurant.decompose.root.onboarding

import org.gbu.restaurant.ui.screens.on_boarding.viewmodel.OnBoardingViewModel


interface OnBoardingComponent {

    val viewModel: OnBoardingViewModel

    fun onBoarded()

}