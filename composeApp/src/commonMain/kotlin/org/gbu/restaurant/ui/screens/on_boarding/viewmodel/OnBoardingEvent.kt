package org.gbu.restaurant.ui.screens.on_boarding.viewmodel

import org.gbu.restaurant.business.core.ViewEvent

sealed class OnBoardingEvent : ViewEvent {
    data object GetStarted : OnBoardingEvent()
}