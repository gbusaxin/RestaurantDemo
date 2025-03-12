package org.gbu.restaurant.decompose.phoneverification

import org.gbu.restaurant.viewmodels.VerifyPhoneViewModel

interface PhoneVerificationComponent {

    val verifyPhoneViewModel: VerifyPhoneViewModel

    fun onVerificationCompleted()

}