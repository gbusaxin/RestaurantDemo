package org.gbu.restaurant.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.gbu.restaurant.data.repo.UserRepository
import org.gbu.restaurant.ui.states.LoginUIState
import org.gbu.restaurant.utils.encrypt
import org.gbu.restaurant.utils.validatePassword
import org.gbu.restaurant.utils.validatePhone

class LoginViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val uiState: StateFlow<LoginUIState> = _uiState
    private val _phone = mutableStateOf("421950290020")
    val phone: State<String> = _phone
    private val _password = mutableStateOf("sssssss")
    val password: State<String> = _password

    fun authenticateUser(phone: String, password: String) {
        if (validatePhone(phone)) {
            if (validatePassword(password)) {
                val encryptedPhone = phone.encrypt()
                val encryptedPassword = password.encrypt()
                _uiState.update { LoginUIState.Loading }
                viewModelScope.launch {
                    UserRepository.checkUserAuthentication(
                        encryptedPhone = encryptedPhone,
                        encryptedPassword = encryptedPassword
                    ).let {
                        it?.let { user ->
                            _uiState.update { LoginUIState.Authenticated(user) }
                        } ?: run {
                            _uiState.update { LoginUIState.Error.Auth("No user found") }
                        }
                    }
                }
            } else {
                _uiState.update { LoginUIState.Error.Password("Password is not valid") }
            }
        } else {
            _uiState.update { LoginUIState.Error.Phone("Phone is not valid") }
        }
    }

    fun updatePhone(phone: String) {
        if (_uiState.value is LoginUIState.Error.Phone) {
            _uiState.value = LoginUIState.Idle
        }
        _phone.value = phone
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}