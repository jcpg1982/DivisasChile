package pe.com.master.machines.register.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import pe.com.master.machines.domain.sqlite.GetEmailUserUseCase
import pe.com.master.machines.domain.sqlite.InsertUserUseCase
import pe.com.master.machines.model.model.User
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val getEmailUserUseCase: GetEmailUserUseCase,
    private val insertUserUseCase: InsertUserUseCase,
) : ViewModel() {

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