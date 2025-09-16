package pe.com.master.machines.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pe.com.master.machines.common.response.Resource
import pe.com.master.machines.common.utils.messageError
import pe.com.master.machines.domain.sqlite.LoginUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private var _loginUserState = MutableStateFlow<LoginState>(LoginState.First)
    val loginUserState get() = _loginUserState.asStateFlow()

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

    fun loginUser() {
        viewModelScope.launch {
            loginUseCase.invoke(emailUser.value, passwordUser.value)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _loginUserState.update { LoginState.Loading("Iniciando sesion") }
                }
                .catch { e ->
                    _loginUserState.update { LoginState.Error(e.message ?: "Error inesperado") }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> _loginUserState.update { LoginState.Error(res.messageError) }

                        is Resource.Success -> {
                            if (res.data == null) _loginUserState.update { LoginState.Error("Usuario no encontrado") }
                            else _loginUserState.update { LoginState.Success }
                        }
                    }
                }
        }
    }
}