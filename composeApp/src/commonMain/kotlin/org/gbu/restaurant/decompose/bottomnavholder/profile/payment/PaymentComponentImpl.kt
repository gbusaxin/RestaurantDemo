package org.gbu.restaurant.decompose.bottomnavholder.profile.payment

import com.arkivanov.decompose.ComponentContext
import org.gbu.restaurant.ui.screens.payment_method.viewmodel.PaymentMethodViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PaymentComponentImpl(
    componentContext: ComponentContext,
    private val onPopUp: () -> Unit
): PaymentComponent, KoinComponent, ComponentContext by componentContext {
    override val viewModel: PaymentMethodViewModel by inject<PaymentMethodViewModel>()
    override fun popUp() {
        onPopUp()
    }
}