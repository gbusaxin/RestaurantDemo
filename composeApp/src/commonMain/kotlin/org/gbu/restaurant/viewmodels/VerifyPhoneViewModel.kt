package org.gbu.restaurant.viewmodels

import androidx.compose.runtime.mutableStateOf
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.gbu.restaurant.ui.states.VerifyPhoneUIState

class VerifyPhoneViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)

    val uiState = mutableStateOf<VerifyPhoneUIState>(VerifyPhoneUIState.Idle)
    val otp = mutableStateOf("")

    override fun onDestroy() {
        viewModelScope.cancel()
    }

    fun validateOtp(otp: String) {
        uiState.value = VerifyPhoneUIState.Verifying
        viewModelScope.launch {
            delay(3000) // TODO implement phone verify
            uiState.value =
                if (otp == "2025") VerifyPhoneUIState.Verified else VerifyPhoneUIState.Error
        }
    }

    fun updateOtp(value: String) {
        otp.value = value
    }
}