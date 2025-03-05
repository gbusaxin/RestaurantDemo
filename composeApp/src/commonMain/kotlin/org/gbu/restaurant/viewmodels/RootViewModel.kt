package org.gbu.restaurant.viewmodels

import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import org.gbu.restaurant.data.entity.User

class RootViewModel : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Unconfined)
    private val _user = MutableStateFlow<User?>(null)
    val user : StateFlow<User?> = _user

    fun updateLoggedUser(user: User?){
        _user.update { user }
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

}
val LocalUser = compositionLocalOf<User?> { null }