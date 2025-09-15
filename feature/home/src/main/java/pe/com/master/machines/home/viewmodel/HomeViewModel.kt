package pe.com.master.machines.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    //private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _emailUser = MutableStateFlow("")
    val emailUser = _emailUser.asStateFlow()
    private val _passwordUser = MutableStateFlow("")
    val passwordUser = _passwordUser.asStateFlow()
    private val _enabledButton = combine(emailUser, passwordUser) { email, password ->
        email.isNotEmpty() && password.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val enabledButton = _enabledButton

    fun updateEmailUser(email: String) {
        _emailUser.update { email }
    }

    fun updatePasswordUser(password: String) {
        _passwordUser.update { password }
    }

    /*fun loginUser() {
        viewModelScope.launch {
            loginUseCase.invoke(emailUser.value, passwordUser.value)
                .flowOn(Dispatchers.IO)
                .onStart { }
                .catch { e -> }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> {}

                        is Resource.Success -> {}
                    }
                }
        }
    }*/

}