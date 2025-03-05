package org.gbu.restaurant.decompose.onboarding

import org.gbu.restaurant.viewmodels.OnBoardingViewModel

interface OnBoardingComponent {

    val viewModel: OnBoardingViewModel

    fun onBoarded()

}