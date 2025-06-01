package org.gbu.restaurant.decompose.bottomnavholder.profile.payment

import org.gbu.restaurant.ui.screens.payment_method.viewmodel.PaymentMethodViewModel

interface PaymentComponent {
    val viewModel: PaymentMethodViewModel
    fun popUp()
}