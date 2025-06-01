package org.gbu.restaurant.decompose.root.phoneverification

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import org.gbu.restaurant.viewmodels.VerifyPhoneViewModel

class PhoneVerificationComponentImpl(
    componentContext: ComponentContext,
    val onVerifiedSuccessfully: () -> Unit
) : PhoneVerificationComponent, ComponentContext by componentContext {

    override val verifyPhoneViewModel: VerifyPhoneViewModel
        get() = instanceKeeper.getOrCreate { VerifyPhoneViewModel() }

    override fun onVerificationCompleted() {
        onVerifiedSuccessfully()
    }

}