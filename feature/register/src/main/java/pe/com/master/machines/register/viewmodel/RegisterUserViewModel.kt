package pe.com.master.machines.register.viewmodel

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
import pe.com.master.machines.domain.sqlite.GetEmailUserUseCase
import pe.com.master.machines.domain.sqlite.InsertUserUseCase
import pe.com.master.machines.model.model.User
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val getEmailUserUseCase: GetEmailUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
) : ViewModel() {
    
    
    private var _registerUserState = MutableStateFlow<RegisterState>(RegisterState.First)
    val registerUserState get() = _registerUserState.asStateFlow()
    
    private val _user = MutableStateFlow(User())
    val user = _user.asStateFlow()
    private val _repeatPassword = MutableStateFlow("")
    val repeatPassword = _repeatPassword.asStateFlow()
    
    private val _enabledButton = combine(user, repeatPassword) { user, pass ->
        user.firstName.isNotEmpty() &&
                user.lastName.isNotEmpty() &&
                user.email.isNotEmpty() &&
                user.password.isNotEmpty() &&
                user.password == pass
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    val enabledButton = _enabledButton
    
    fun updateUser(user: User) {
        _user.update { user }
    }
    
    fun updateRepeatPassword(repeatPassword: String) {
        _repeatPassword.update { repeatPassword }
    }
    
    fun registerUser() {
        viewModelScope.launch {
            getEmailUserUseCase.invoke(user.value.email)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _registerUserState.update { RegisterState.Loading("Verificando existencia del email") }
                }
                .catch { e ->
                    _registerUserState.update {
                        RegisterState.Error(e.message ?: "Error inesperado")
                    }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> _registerUserState.update {
                            RegisterState.Error(res.messageError)
                        }
                        
                        is Resource.Success -> {
                            if (res.data) {
                                _registerUserState.update {
                                    RegisterState.Error("El correo ya se encuentra registrado en nuestro sistema use otro correo")
                                }
                            } else {
                                saveDataUser()
                            }
                        }
                    }
                }
        }
    }
    
    fun saveDataUser() {
        viewModelScope.launch {
            insertUserUseCase.invoke(user.value)
                .flowOn(Dispatchers.IO)
                .onStart {
                    _registerUserState.update { RegisterState.Loading("Guardando informacion en la base de datos") }
                }
                .catch { e ->
                    _registerUserState.update {
                        RegisterState.Error(e.message ?: "Error inesperado")
                    }
                }
                .collect { res ->
                    when (res) {
                        is Resource.Error -> _registerUserState.update {
                            RegisterState.Error(res.messageError)
                        }
                        
                        is Resource.Success -> _registerUserState.update {
                            RegisterState.Success
                        }
                    }
                }
        }
    }
    
}