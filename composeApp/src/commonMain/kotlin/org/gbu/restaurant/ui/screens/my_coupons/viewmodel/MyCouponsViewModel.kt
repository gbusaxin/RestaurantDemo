package org.gbu.restaurant.ui.screens.my_coupons.viewmodel

import org.gbu.restaurant.business.core.BaseViewModel

class MyCouponsViewModel(

): BaseViewModel<MyCouponsEvent, MyCouponsState, Nothing>() {
    override fun setInitialState(): MyCouponsState = MyCouponsState()

    override fun onTriggerEvent(event: MyCouponsEvent) {
        when(event){
            is MyCouponsEvent.OnRetryNetwork -> TODO()
            is MyCouponsEvent.OnUpdateNetworkState -> TODO()
        }
    }
}