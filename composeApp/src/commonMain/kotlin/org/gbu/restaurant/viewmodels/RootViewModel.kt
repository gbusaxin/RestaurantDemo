package org.gbu.restaurant.viewmodels

import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.gbu.restaurant.business.data.entity.User
import org.gbu.restaurant.ui.token_manager.TokenManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RootViewModel : InstanceKeeper.Instance, KoinComponent {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user
    val tokenManager: TokenManager by inject<TokenManager>()

    fun updateLoggedUser(user: User?){
        _user.update { user }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

}
val LocalUser = compositionLocalOf<User?> { null }