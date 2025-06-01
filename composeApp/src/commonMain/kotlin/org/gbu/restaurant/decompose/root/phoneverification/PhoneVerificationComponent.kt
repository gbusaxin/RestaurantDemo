package org.gbu.restaurant.decompose.root.phoneverification

import org.gbu.restaurant.viewmodels.VerifyPhoneViewModel

interface PhoneVerificationComponent {

    val verifyPhoneViewModel: VerifyPhoneViewModel

    fun onVerificationCompleted()

}