package org.gbu.restaurant.business.core

sealed class UIComponentState {

    data object Show: UIComponentState()

    data object HalfShow: UIComponentState()

    data object Hide: UIComponentState()

}